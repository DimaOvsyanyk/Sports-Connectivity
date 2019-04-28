package com.dimaoprog.sportsconnectivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbWorkouts.UserDao;
import com.dimaoprog.sportsconnectivity.dbWorkouts.AppDatabase;
import com.dimaoprog.sportsconnectivity.workoutViews.DetailWorkoutFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutAddFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutsListAdapter;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutsListFragment;

import java.util.List;

import static com.dimaoprog.sportsconnectivity.dbEntities.User.NOTSTAY;
import static com.dimaoprog.sportsconnectivity.dbEntities.User.STAY;

public class ForWorkoutsActivity extends AppCompatActivity implements WorkoutsListAdapter.IDetailWorkoutListener,
        WorkoutsListFragment.AddListener, WorkoutAddFragment.AddWorkoutListener, NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_MAIN = "applog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_workouts);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
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
        bottomNavigationView.setSelectedItemId(R.id.menu_workouts);
        Toast.makeText(this, "Hello " + User.getACTIVEUSER().getFirstName(), Toast.LENGTH_SHORT).show();
        openWorkoutsListFragment();
    }


    public void openProfileFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, ProfileFragment.newInstance())
                .commit();
    }

    public void openFoodFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, FoodFragment.newInstance())
                .commit();
    }

    public void openTrainerFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, TrainerFragment.newInstance())
                .commit();
    }

    @Override
    public void openWorkoutAddFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, WorkoutAddFragment.newInstance(this))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openWorkoutsListFragment() {
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, WorkoutsListFragment.newInstance(this, this))
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
                .replace(R.id.container_fr, DetailWorkoutFragment.newInstance(workoutId))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        AppDatabase db = AppDatabase.getInstance(this);
        UserDao userDao = db.userDao();

        switch (menuItem.getItemId()) {
            case R.id.menu_active_user:
                Log.d(LOG_MAIN, User.getACTIVEUSER().toString());
                break;
            case R.id.menu_all_users:
                List<User> allUsers = userDao.getAllUsers();
                for (int i = 0; i < allUsers.size(); i++) {
                    String stringUser = allUsers.get(i).toString();
                    Log.d(LOG_MAIN, stringUser);
                }
                break;
            case R.id.menu_logoff:
                if (User.getACTIVEUSER().getStayInSystem() == STAY) {
                    User.getACTIVEUSER().setStayInSystem(NOTSTAY);
                    userDao.update(User.getACTIVEUSER());
                }
                Intent intent = new Intent(ForWorkoutsActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
