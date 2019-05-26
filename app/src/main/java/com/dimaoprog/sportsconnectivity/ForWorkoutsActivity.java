package com.dimaoprog.sportsconnectivity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dimaoprog.sportsconnectivity.dagger.AppComponentBuild;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbRepos.UserRepository;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.LoginFragment;
import com.dimaoprog.sportsconnectivity.profileViews.ProfileFragment;
import com.dimaoprog.sportsconnectivity.receiptViews.MyReceiptsFragment;
import com.dimaoprog.sportsconnectivity.foodViews.FoodFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dimaoprog.sportsconnectivity.Constants.STAY;

public class ForWorkoutsActivity extends AppCompatActivity implements FragmentNaviManager.ShowNewFragmentListener {

    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_workouts);
        AppComponentBuild.createAppComponent(this);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_account:
                    showMainFragments(new ProfileFragment());
                    break;
                case R.id.menu_workouts:
                    showMainFragments(new WorkoutsListFragment());
                    break;
                case R.id.menu_food:
                    clearBackStack();
                    showMainFragments(new FoodFragment());
                    break;
                case R.id.menu_receipts:
                    showMainFragments(new MyReceiptsFragment());
                    break;
            }
            return true;
        });
        if (savedInstanceState == null) {
            if (checkSomeOneInSystem() != null) {
                User.setACTIVEUSER(checkSomeOneInSystem());
                bottomNavigationView.setVisibility(View.VISIBLE);
                showMainFragments(new ProfileFragment());
            } else {
                bottomNavigationView.setVisibility(View.GONE);
                showMainFragments(new LoginFragment());
            }
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    public void showBottomNaviView(boolean show) {
        if (show) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        } else {
            bottomNavigationView.setVisibility(View.GONE);
        }
    }


    private User checkSomeOneInSystem() {
        UserRepository userRepo = new UserRepository(this.getApplication());
        return userRepo.getByStayIn(STAY);
    }

    public void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }

    public void showMainFragments(Fragment fragment) {
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, fragment)
                .commit();
    }

    @Override
    public void showFragmentFromActivity(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, fragment)
                .addToBackStack(null)
                .commit();
    }
}
