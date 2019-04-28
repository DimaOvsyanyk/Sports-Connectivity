package com.dimaoprog.sportsconnectivity.workoutViews;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WorkoutsListFragment extends Fragment {

    WorkoutsListAdapter.IDetailWorkoutListener detailListener;
    AddListener addListener;

    public static WorkoutsListFragment newInstance(WorkoutsListAdapter.IDetailWorkoutListener detailListener, AddListener addListener) {
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

    private Unbinder unbinder;
    @BindView(R.id.rvNewsList)
    RecyclerView workoutList;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    private WorkoutsListViewModel wlViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workouts_list, container, false);

        unbinder = ButterKnife.bind(this, v);
        workoutList.setLayoutManager(new LinearLayoutManager(getContext()));
        final WorkoutsListAdapter workoutsListAdapter = new WorkoutsListAdapter(detailListener);
        workoutList.setAdapter(workoutsListAdapter);

        wlViewModel = ViewModelProviders.of(this).get(WorkoutsListViewModel.class);
        wlViewModel.getAllWorkouts().observe(this, new Observer<List<Workout>>() {
            @Override
            public void onChanged(@Nullable List<Workout> workouts) {
                workoutsListAdapter.submitList(workouts);
            }
        });

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    wlViewModel.addNewWorkoutsFromJson();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                swipeLayout.setRefreshing(false);
            }
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
        }).attachToRecyclerView(workoutList);

        return v;
    }

    @OnClick(R.id.fab_add)
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add:
                rotate(v);
                addListener.openWorkoutAddFragment();
                break;
        }
    }

    private void rotate(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
        } else {
            view.animate().setDuration(200).rotation(0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
