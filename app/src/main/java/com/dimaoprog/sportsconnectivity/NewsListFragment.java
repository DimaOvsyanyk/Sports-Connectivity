package com.dimaoprog.sportsconnectivity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.dbUsers.App;
import com.dimaoprog.sportsconnectivity.dbUsers.User;
import com.dimaoprog.sportsconnectivity.dbUsers.UserDao;
import com.dimaoprog.sportsconnectivity.dbUsers.UserDatabase;
import com.dimaoprog.sportsconnectivity.manager.NewsManager;
import com.dimaoprog.sportsconnectivity.news.News;

import java.util.List;
import java.util.Objects;

import static com.dimaoprog.sportsconnectivity.dbUsers.User.NOTSTAY;
import static com.dimaoprog.sportsconnectivity.dbUsers.User.STAY;

public class NewsListFragment extends Fragment implements View.OnClickListener {

    public static final String USERS_FROM_BD = "userslog";

    NewsAdapter.IDetailNewsListener detailListener;
    LogOffListener logOffListener;
    User activeUser;

    public static NewsListFragment newInstance(NewsAdapter.IDetailNewsListener detailListener, LogOffListener logOffListener, User activeUser) {
        NewsListFragment fragment = new NewsListFragment();
        fragment.setDetailListener(detailListener);
        fragment.setLogOffListener(logOffListener);
        fragment.setActiveUser(activeUser);
        return fragment;
    }

    public void setDetailListener(NewsAdapter.IDetailNewsListener detailListener) {
        this.detailListener = detailListener;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public interface LogOffListener {
        void openLoginFragment();
    }

    public void setLogOffListener(LogOffListener logOffListener) {
        this.logOffListener = logOffListener;
    }

    RecyclerView newsList;
    FloatingActionButton fabAdd;
    Button btnAllUsersLog;
    Button btnActiveUserLog;
    Button btnLogoff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);

        initViews(v);
        newsList.setAdapter(new NewsAdapter(detailListener));
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        Animation in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_left);
        newsList.startAnimation(in);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateFab();
                showDialogNewsAdd();
            }
        });
        btnAllUsersLog.setOnClickListener(this);
        btnActiveUserLog.setOnClickListener(this);
        btnLogoff.setOnClickListener(this);

        Toast.makeText(getContext(), "Hello " + activeUser.firstName, Toast.LENGTH_SHORT).show();

        return v;
    }

    private void initViews(View v) {
        newsList = v.findViewById(R.id.rvNewsList);
        fabAdd = v.findViewById(R.id.fab_add);
        btnAllUsersLog = v.findViewById(R.id.btn_log_all_users);
        btnActiveUserLog  = v.findViewById(R.id.btn_log_active_user);
        btnLogoff = v.findViewById(R.id.btn_logoff);
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView.Adapter currentAdapter = newsList.getAdapter();
        if (currentAdapter != null)
            currentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        UserDatabase db = App.getInstance().getDatabase();
        UserDao userDao = db.userDao();
        switch (v.getId()) {
            case R.id.btn_log_all_users:
                List<User> allUsers = userDao.getAllUsers();
                for (int i = 0; i < allUsers.size(); i++) {
                    String stringUser = String.valueOf(allUsers.get(i).id) + " " +
                            allUsers.get(i).firstName + " " +
                            allUsers.get(i).secondName + " " +
                            allUsers.get(i).eMail + " " +
                            allUsers.get(i).password + " " +
                            allUsers.get(i).stayInSystem;
                    Log.d(USERS_FROM_BD, stringUser);
                }
                break;
            case R.id.btn_log_active_user:
                String stringUser = String.valueOf(activeUser.id) + " " +
                        activeUser.firstName + " " +
                        activeUser.secondName + " " +
                        activeUser.eMail + " " +
                        activeUser.password + " " +
                        activeUser.stayInSystem;
                Log.d(USERS_FROM_BD, stringUser);
                break;
            case R.id.btn_logoff:
                if (activeUser.stayInSystem == STAY) {
                    activeUser.stayInSystem = NOTSTAY;
                    userDao.update(activeUser);
                }
                logOffListener.openLoginFragment();
                break;
        }
    }


    private void rotateFab() {
        if (fabAdd.getRotation() == 0) {
            fabAdd.animate().setDuration(200).rotation(180);
        } else {
            fabAdd.animate().setDuration(200).rotation(0);
        }
    }

    private void showDialogNewsAdd() {
        final Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        dialog.setContentView(R.layout.dialog_workouk_add);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        final Spinner newTitle = dialog.findViewById(R.id.spinner_title);
        final EditText newShort = dialog.findViewById(R.id.etNewShort);
//        final EditText newLong = dialog.findViewById(R.id.etNewLong);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        Button btnAdd = dialog.findViewById(R.id.btn_confirm_add);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newFlag = newTitle.getSelectedItemPosition();
                String shortN = newShort.getText().toString();
//                String longN = newLong.getText().toString();
//                Date currentTime = Calendar.getInstance().getTime();
                News newsToAdd = new News(newFlag, shortN);
                NewsManager.getAllNews().add(newsToAdd);
                dialog.dismiss();
                onResume();
            }
        });
        dialog.show();
    }
}
