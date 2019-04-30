package com.dimaoprog.sportsconnectivity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.dimaoprog.sportsconnectivity.foodViews.DetailFoodFragment;
import com.dimaoprog.sportsconnectivity.foodViews.FoodListAdapter;
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
        WorkoutsListFragment.AddListener, WorkoutAddFragment.AddWorkoutListener, FoodListAdapter.DetailFoodListener {

    public static final String LOG_MAIN = "applog";

    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavigationView;

    private static final int FULL_SCREEN_CONTAINER = R.id.container_fr;

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
                .replace(FULL_SCREEN_CONTAINER, ProfileFragment.newInstance())
                .commit();
    }

    public void openFoodFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(FULL_SCREEN_CONTAINER, FoodFragment.newInstance(this))
                .commit();
    }

    public void openTrainerFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(FULL_SCREEN_CONTAINER, TrainerFragment.newInstance())
                .commit();
    }

    @Override
    public void openWorkoutAddFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(FULL_SCREEN_CONTAINER, WorkoutAddFragment.newInstance(this))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openWorkoutsListFragment() {
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(FULL_SCREEN_CONTAINER, WorkoutsListFragment.newInstance(this, this))
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
                .replace(FULL_SCREEN_CONTAINER, DetailWorkoutFragment.newInstance(workoutId))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openDetailFoodFragment(long menuId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(FULL_SCREEN_CONTAINER, DetailFoodFragment.newInstance(menuId))
                .addToBackStack(null)
                .commit();
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        AppDatabase db = AppDatabase.getInstance(this);
//        UserDao userDao = db.userDao();
//
//        switch (menuItem.getItemId()) {
//            case R.id.menu_active_user:
//                Log.d(LOG_MAIN, User.getACTIVEUSER().toString());
//                break;
//            case R.id.menu_all_users:
//                List<User> allUsers = userDao.getAllUsers();
//                for (int i = 0; i < allUsers.size(); i++) {
//                    String stringUser = allUsers.get(i).toString();
//                    Log.d(LOG_MAIN, stringUser);
//                }
//                break;
//            case R.id.menu_logoff:
//                if (User.getACTIVEUSER().getStayInSystem() == STAY) {
//                    User.getACTIVEUSER().setStayInSystem(NOTSTAY);
//                    userDao.update(User.getACTIVEUSER());
//                }
//                Intent intent = new Intent(ForWorkoutsActivity.this, LoginActivity.class);
//                startActivity(intent);
//                break;
//        }
//        drawer.closeDrawer(GravityCompat.START);
//
//        return true;
//    }
}
