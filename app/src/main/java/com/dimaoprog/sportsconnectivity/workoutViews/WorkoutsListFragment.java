package com.dimaoprog.sportsconnectivity.workoutViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentWorkoutsListBinding;

import org.json.JSONException;

import java.io.IOException;

public class WorkoutsListFragment extends Fragment {

    WorkoutsListAdapter.IDetailWorkoutListener detailListener;
    AddListener addListener;

    public static WorkoutsListFragment newInstance(WorkoutsListAdapter.IDetailWorkoutListener detailListener,
                                                   AddListener addListener) {
        WorkoutsListFragment fragment = new WorkoutsListFragment();
        fragment.setDetailListener(detailListener);
        fragment.setAddListener(addListener);
        return fragment;
    }

    public void setDetailListener(WorkoutsListAdapter.IDetailWorkoutListener detailListener) {
        this.detailListener = detailListener;
    }

    public interface AddListener {
        void openWorkoutAddFragment();
    }

    public void setAddListener(AddListener addListener) {
        this.addListener = addListener;
    }

    private WorkoutsListViewModel wlViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentWorkoutsListBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_workouts_list, container, false);
        final WorkoutsListAdapter workoutsListAdapter = new WorkoutsListAdapter(detailListener);
        binding.rvWorkoutsList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvWorkoutsList.setAdapter(workoutsListAdapter);

        wlViewModel = ViewModelProviders.of(this).get(WorkoutsListViewModel.class);
        wlViewModel.getAllWorkouts().observe(this, workouts -> workoutsListAdapter.submitList(workouts));
        binding.swipeLayout.setOnRefreshListener(() -> {
            try {
                wlViewModel.addNewWorkoutsFromJson(getContext());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            binding.swipeLayout.setRefreshing(false);
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                wlViewModel.delete(workoutsListAdapter.getWorkoutAtPos(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(binding.rvWorkoutsList);
        binding.fabAdd.setOnClickListener(v -> addListener.openWorkoutAddFragment());
        return binding.getRoot();
    }
}