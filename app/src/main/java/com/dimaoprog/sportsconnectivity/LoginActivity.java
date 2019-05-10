package com.dimaoprog.sportsconnectivity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbRepos.UserRepository;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.LoginFragment;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.RegistrationFragment;

import static com.dimaoprog.sportsconnectivity.Constants.STAY;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnPressedButtonListener,
        RegistrationFragment.RegistrationCompleteListener {

    private int container_id = R.id.container_fr_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            if (checkSomeOneInSystem() != null) {
                User.setACTIVEUSER(checkSomeOneInSystem());
                openForWorkoutsActivity();
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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void openLoginFragment() {
        User.setACTIVEUSER(null);
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container_id, LoginFragment.newInstance(this))
                .commit();
    }

    public void openForWorkoutsActivity() {
        clearBackStack();
        Intent intent = new Intent(LoginActivity.this, ForWorkoutsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void openRegistrationFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container_id, RegistrationFragment.newInstance(this))
                .addToBackStack(null)
                .commit();
    }

    public void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onLoginClick() {
        openForWorkoutsActivity();
    }

    @Override
    public void onRegisterClick() {
        openRegistrationFragment();
    }
}
