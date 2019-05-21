package com.dimaoprog.sportsconnectivity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbRepos.UserRepository;
import com.dimaoprog.sportsconnectivity.foodViews.DetailFoodFragment;
import com.dimaoprog.sportsconnectivity.foodViews.FoodListAdapter;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.LoginFragment;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.RegistrationFragment;
import com.dimaoprog.sportsconnectivity.profileViews.AddMeasurementsFragment;
import com.dimaoprog.sportsconnectivity.profileViews.ProfileFragment;
import com.dimaoprog.sportsconnectivity.receiptViews.MyReceiptDetailFragment;
import com.dimaoprog.sportsconnectivity.receiptViews.MyReceiptsListAdapter;
import com.dimaoprog.sportsconnectivity.receiptViews.ReceiptAddNewFragment;
import com.dimaoprog.sportsconnectivity.receiptViews.ReceiptDetailFragment;
import com.dimaoprog.sportsconnectivity.receiptViews.ReceiptListAdapter;
import com.dimaoprog.sportsconnectivity.receiptViews.MyReceiptsFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.DetailWorkoutFragment;
import com.dimaoprog.sportsconnectivity.foodViews.FoodFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutAddFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutsListAdapter;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutsListFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.doWorkout.DoWorkoutFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dimaoprog.sportsconnectivity.Constants.STAY;

public class ForWorkoutsActivity extends AppCompatActivity implements WorkoutsListAdapter.IDetailWorkoutListener,
        WorkoutsListFragment.AddListener, WorkoutAddFragment.AddWorkoutListener, FoodListAdapter.DetailFoodListener,
        ProfileFragment.ProfileActionListener, DetailWorkoutFragment.StartWorkoutListener,
        RegistrationFragment.RegistrationCompleteListener, LoginFragment.OnPressedButtonListener,
        ReceiptListAdapter.IReceiptPickedListener, MyReceiptsFragment.WantToAddNewReceiptListener,
        MyReceiptsListAdapter.IFavoriteReceiptPickedListener {

    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavigationView;

    private int fullScreenContainerId = R.id.container_fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_workouts);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
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
                case R.id.menu_receipts:
                    openReceiptFragment();
                    break;
            }
            return true;
        });
        if (savedInstanceState == null) {
            if (checkSomeOneInSystem() != null) {
                User.setACTIVEUSER(checkSomeOneInSystem());
                openProfileFragment();
            } else {
                openLoginFragment();
            }
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
    }


    private User checkSomeOneInSystem() {
        UserRepository userRepo = new UserRepository(this.getApplication());
        return userRepo.getByStayIn(STAY);
    }

    @Override
    public void openLoginFragment() {
        bottomNavigationView.setVisibility(View.GONE);
        User.setACTIVEUSER(null);
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, LoginFragment.newInstance(this))
                .commit();
    }

    public void openRegistrationFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, RegistrationFragment.newInstance(this))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openReceiptAddNewFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, ReceiptAddNewFragment.newInstance(this))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onLoginClick() {
        bottomNavigationView.setVisibility(View.VISIBLE);
        openProfileFragment();
    }

    @Override
    public void onRegisterClick() {
        openRegistrationFragment();
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
        bottomNavigationView.setVisibility(View.VISIBLE);
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, ProfileFragment.newInstance(this))
                .commit();
    }

    public void openFoodFragment() {
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, FoodFragment.newInstance(this))
                .commit();
    }

    public void openReceiptFragment() {
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, MyReceiptsFragment.newInstance(this,
                        this))
                .commit();
    }

    @Override
    public void openWorkoutAddFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, WorkoutAddFragment.newInstance(this))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openWorkoutsListFragment() {
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, WorkoutsListFragment.newInstance(this, this))
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
                .replace(fullScreenContainerId, DetailWorkoutFragment.newInstance(workoutId, this))
                .addToBackStack(null)
                .commit();
    }

    public void openReceiptDetailFragment(String id) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, ReceiptDetailFragment.newInstance(id))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openDetailFoodFragment(long menuId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, DetailFoodFragment.newInstance(menuId))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openAddMeasurementFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, AddMeasurementsFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openDoWorkoutFragment(long workoutId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, DoWorkoutFragment.newInstance(workoutId))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openMyReceiptDetailFragment(long id) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fullScreenContainerId, MyReceiptDetailFragment.newInstance(id))
                .addToBackStack(null)
                .commit();
    }
}
