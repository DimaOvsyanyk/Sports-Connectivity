package com.dimaoprog.sportsconnectivity;

import android.support.annotation.NonNull;
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
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.LoginFragment;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.RegistrationFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.DetailWorkoutFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutAddFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutsAdapter;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutsListFragment;

import java.util.List;

import static com.dimaoprog.sportsconnectivity.dbEntities.User.NOTSTAY;
import static com.dimaoprog.sportsconnectivity.dbEntities.User.STAY;

public class MainActivity extends AppCompatActivity implements WorkoutsAdapter.IDetailWorkoutListener,
        LoginFragment.OnPressedButtonListener, RegistrationFragment.RegistrationCompleteListener,
        WorkoutsListFragment.AddListener, WorkoutAddFragment.AddWorkoutListener, NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_MAIN = "applog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (checkSomeOneInSystem() != null) {
            User.setACTIVEUSER(checkSomeOneInSystem());
            openWorkoutsListFragment();
        } else {
            openLoginFragment();
        }
    }

    @Override
    public void openLoginFragment() {
        User.setACTIVEUSER(null);
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, LoginFragment.newInstance(this))
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
    public void openRegistrationFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, RegistrationFragment.newInstance(this))
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
        Toast.makeText(this, "Hello " + User.getACTIVEUSER().getFirstName(), Toast.LENGTH_SHORT).show();
    }

    public void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void openDetailWorkoutFragment(int i) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, DetailWorkoutFragment.newInstance(i))
                .addToBackStack(null)
                .commit();
    }

    private User checkSomeOneInSystem() {
        AppDatabase db = AppDatabase.getInstance(this);
        UserDao userDao = db.userDao();
        return userDao.getByStayIn(User.STAY);
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
                openLoginFragment();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
