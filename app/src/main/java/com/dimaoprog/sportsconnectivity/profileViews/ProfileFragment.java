package com.dimaoprog.sportsconnectivity.profileViews;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dimaoprog.sportsconnectivity.Constants.LOG_MAIN;

public class ProfileFragment extends Fragment {

    ProfileActionListener profileActionListener;

    public static ProfileFragment newInstance(ProfileActionListener profileActionListener) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setProfileActionListener(profileActionListener);
        return fragment;
    }

    public void setProfileActionListener(ProfileActionListener profileActionListener) {
        this.profileActionListener = profileActionListener;
    }

    public interface ProfileActionListener {
        void openLoginActivity();

        void openAddMeasurementFragment();
    }

    private ProfileViewModel pViewModel;
    private Unbinder unbinder;
    @BindView(R.id.txt_user_name)
    TextView txtUserName;
    @BindView(R.id.img_workout_statistic_detail)
    ImageView imgWorkoutsStatistics;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, v);
        pViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtUserName.setText(pViewModel.getNameSurname());
    }

    @OnClick({R.id.btn_active_user_to_log, R.id.btn_all_users_to_log})
    void onClickToLog(View view) {
        switch (view.getId()) {
            case R.id.btn_active_user_to_log:
                Log.d(LOG_MAIN, User.getACTIVEUSER().toString());
                break;
            case R.id.btn_all_users_to_log:
                for (int i = 0; i < pViewModel.getAllUsers().size(); i++) {
                    Log.d(LOG_MAIN, pViewModel.getAllUsers().get(i).toString());
                }
                break;
        }
    }

    @OnClick(R.id.btn_logoff)
    void onLogoffClick(View view) {
        pViewModel.logoffAction();
        profileActionListener.openLoginActivity();
    }

    @OnClick(R.id.btn_add_new_measurements)
    void addMeasurement(View view) {
        profileActionListener.openAddMeasurementFragment();
    }

    @OnClick(R.id.btn_detail_workout_statistic)
    void openStatistic(View view) {
        imgWorkoutsStatistics.setVisibility(rotate(view));
    }

    private int rotate(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return View.VISIBLE;
        } else {
            view.animate().setDuration(200).rotation(0);
            return View.GONE;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
