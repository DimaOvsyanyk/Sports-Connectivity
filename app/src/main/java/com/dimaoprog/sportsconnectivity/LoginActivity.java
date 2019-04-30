package com.dimaoprog.sportsconnectivity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbRepos.AppDatabase;
import com.dimaoprog.sportsconnectivity.dbRepos.UserDao;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.LoginFragment;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.RegistrationFragment;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnPressedButtonListener,
        RegistrationFragment.RegistrationCompleteListener {

    private int container = R.id.container_fr_login;

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

    @Override
    public void openLoginFragment() {
        User.setACTIVEUSER(null);
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container, LoginFragment.newInstance(this))
                .commit();
    }

    @Override
    public void openForWorkoutsActivity() {
        Intent intent = new Intent(LoginActivity.this, ForWorkoutsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void openRegistrationFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container, RegistrationFragment.newInstance(this))
                .addToBackStack(null)
                .commit();
    }

    public void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }

    private User checkSomeOneInSystem() {
        AppDatabase db = AppDatabase.getInstance(this);
        UserDao userDao = db.userDao();
        return userDao.getByStayIn(User.STAY);
    }
}
