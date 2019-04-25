package com.dimaoprog.sportsconnectivity.workoutViews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbWorkouts.AppDatabase;
import com.dimaoprog.sportsconnectivity.dbWorkouts.JSONWorkoutReader;
import com.dimaoprog.sportsconnectivity.dbWorkouts.WorkoutDao;
import com.dimaoprog.sportsconnectivity.manager.WorkoutsManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WorkoutsListFragment extends Fragment {


    WorkoutsAdapter.IDetailWorkoutListener detailListener;
    AddListener addListener;

    public static WorkoutsListFragment newInstance(WorkoutsAdapter.IDetailWorkoutListener detailListener, AddListener addListener) {
        WorkoutsListFragment fragment = new WorkoutsListFragment();
        fragment.setDetailListener(detailListener);
        fragment.setAddListener(addListener);
        return fragment;
    }

    public void setDetailListener(WorkoutsAdapter.IDetailWorkoutListener detailListener) {
        this.detailListener = detailListener;
    }

    public interface AddListener {
        void openLoginFragment();

        void openWorkoutAddFragment();
    }

    public void setAddListener(AddListener addListener) {
        this.addListener = addListener;
    }

    private Unbinder unbinder;
    public static final int FIRST_WEEK_WORKOUTS = R.raw.week_workouts;
    @BindView(R.id.rvNewsList)
    RecyclerView workoutList;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workouts_list, container, false);

        unbinder = ButterKnife.bind(this, v);
        WorkoutsManager.setAllWorkouts(getContext());
        workoutList.setAdapter(new WorkoutsAdapter(detailListener));
        workoutList.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    JSONWorkoutReader.setWorkoutsFromJSON(getContext(), FIRST_WEEK_WORKOUTS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                WorkoutsManager.setAllWorkouts(getContext());
                RecyclerView.Adapter currentAdapter = workoutList.getAdapter();
                if (currentAdapter != null)
                    currentAdapter.notifyDataSetChanged();
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
                AppDatabase db = AppDatabase.getInstance(getContext());
                WorkoutDao workoutDao = db.workoutDao();
                workoutDao.delete(WorkoutsManager.getAllWorkouts().get(viewHolder.getAdapterPosition()));
                WorkoutsManager.setAllWorkouts(getContext());
                Objects.requireNonNull(workoutList.getAdapter()).notifyDataSetChanged();
            }
        }).attachToRecyclerView(workoutList);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView.Adapter currentAdapter = workoutList.getAdapter();
        if (currentAdapter != null)
            currentAdapter.notifyDataSetChanged();
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
