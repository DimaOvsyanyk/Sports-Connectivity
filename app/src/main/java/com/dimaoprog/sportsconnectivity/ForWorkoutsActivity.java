package com.dimaoprog.sportsconnectivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.dimaoprog.sportsconnectivity.foodViews.DetailFoodFragment;
import com.dimaoprog.sportsconnectivity.foodViews.FoodListAdapter;
import com.dimaoprog.sportsconnectivity.profileViews.AddMeasurementsFragment;
import com.dimaoprog.sportsconnectivity.profileViews.ProfileFragment;
import com.dimaoprog.sportsconnectivity.trainerViews.TrainerFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.DetailWorkoutFragment;
import com.dimaoprog.sportsconnectivity.foodViews.FoodFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutAddFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutsListAdapter;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForWorkoutsActivity extends AppCompatActivity implements WorkoutsListAdapter.IDetailWorkoutListener,
        WorkoutsListFragment.AddListener, WorkoutAddFragment.AddWorkoutListener, FoodListAdapter.DetailFoodListener,
        ProfileFragment.ProfileActionListener {



    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavigationView;

    private int full_screen_container_id = R.id.container_fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_workouts);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_account:
                        openProfileFragment();
                        break;
                    case R.id.menu_workouts:
                        openWorkoutsListFragment();
                        break;
                    case R.id.menu_food:
                        openFoodFragment();
                        break;
                    case R.id.menu_trainer:
                        openTrainerFragment();
                        break;
                }
                return true;
            }
        });
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.menu_workouts);
            openWorkoutsListFragment();
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void openProfileFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(full_screen_container_id, ProfileFragment.newInstance(this))
                .commit();
    }

    public void openFoodFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(full_screen_container_id, FoodFragment.newInstance(this))
                .commit();
    }

    public void openTrainerFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(full_screen_container_id, TrainerFragment.newInstance())
                .commit();
    }

    @Override
    public void openWorkoutAddFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(full_screen_container_id, WorkoutAddFragment.newInstance(this))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openWorkoutsListFragment() {
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(full_screen_container_id, WorkoutsListFragment.newInstance(this, this))
                .commit();
    }

    public void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void openDetailWorkoutFragment(long workoutId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(full_screen_container_id, DetailWorkoutFragment.newInstance(workoutId))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openDetailFoodFragment(long menuId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(full_screen_container_id, DetailFoodFragment.newInstance(menuId))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = new Intent(ForWorkoutsActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void openAddMeasurementFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(full_screen_container_id, AddMeasurementsFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
