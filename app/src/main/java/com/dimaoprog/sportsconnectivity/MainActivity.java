package com.dimaoprog.sportsconnectivity;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.dimaoprog.sportsconnectivity.dbUsers.App;
import com.dimaoprog.sportsconnectivity.dbUsers.User;
import com.dimaoprog.sportsconnectivity.dbUsers.UserDao;
import com.dimaoprog.sportsconnectivity.dbUsers.UserDatabase;
import com.dimaoprog.sportsconnectivity.manager.NewsManager;
import org.json.JSONException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NewsAdapter.IDetailNewsListener,
        LoginFragment.OnPressedButtonListener,
        RegistrationFragment.RegistrationCompleteListener,
        NewsListFragment.LogOffListener {

    public User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            NewsManager.setAllNews(this, R.raw.json_demo_db);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (checkSomeOneInSystem() != null) {
            openNewsListFragment(checkSomeOneInSystem());
        }else {
            openLoginFragment();
        }
    }
    @Override
    public void openLoginFragment() {
        setCurrentUser(null);
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, LoginFragment.newInstance(this))
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
    public void openNewsListFragment(User userToLogin) {
        setCurrentUser(userToLogin);
        clearBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, NewsListFragment.newInstance(this,this, currentUser))
                .commit();
    }

    public void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void openDetailNewsFragment(int i) {
        int container;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            container = R.id.container_fr;
        } else {
            container = R.id.container_detail_fr;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(container, DetailNewsFragment.newInstance(i))
                .addToBackStack(null)
                .commit();
    }

    private User checkSomeOneInSystem() {
        UserDatabase db = App.getInstance().getDatabase();
        UserDao userDao = db.userDao();
        return userDao.getByStayIn(User.STAY);
    }
}
