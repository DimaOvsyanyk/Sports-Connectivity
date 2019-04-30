package com.dimaoprog.sportsconnectivity.profileViews;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dimaoprog.sportsconnectivity.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

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
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        txtUserName.setText(mViewModel.getNameSurname());
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
