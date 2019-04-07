package com.dimaoprog.sportsconnectivity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import com.dimaoprog.sportsconnectivity.manager.NewsManager;
import com.dimaoprog.sportsconnectivity.news.News;
import java.util.Objects;

public class NewsListFragment extends Fragment implements View.OnClickListener {

    NewsAdapter.IDetailNewsListener listener;

    public static NewsListFragment newInstance(NewsAdapter.IDetailNewsListener listener) {
        NewsListFragment fragment = new NewsListFragment();
        fragment.setListener(listener);
        return fragment;
    }

    RecyclerView newsList;
    FloatingActionButton fabAdd;
    FloatingActionButton fabAddWorkout;
    FloatingActionButton fabAddFood;
    FloatingActionButton fabAddNewSizes;
    boolean isFABaddsShow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);

        initViews(v);
        newsList.setAdapter(new NewsAdapter(listener));
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));

        Animation in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_left);
        newsList.startAnimation(in);

        isFABaddsShow = false;
        fabAddsShow(isFABaddsShow);
        fabAdd.setOnClickListener(this);
        fabAddFood.setOnClickListener(this);
        fabAddWorkout.setOnClickListener(this);
        fabAddNewSizes.setOnClickListener(this);

        return v;
    }

    private void initViews(View v) {
        newsList = v.findViewById(R.id.rvNewsList);
        fabAdd = v.findViewById(R.id.fab_add);
        fabAddWorkout = v.findViewById(R.id.fab_add_workout);
        fabAddFood = v.findViewById(R.id.fab_add_food);
        fabAddNewSizes = v.findViewById(R.id.fab_add_new_sizes);
    }

    private void fabAddsShow(boolean show) {
        if (show) {
            fabAddFood.show();
            fabAddNewSizes.show();
            fabAddWorkout.show();
        } else {
            fabAddFood.hide();
            fabAddNewSizes.hide();
            fabAddWorkout.hide();
        }
        this.isFABaddsShow = !show;
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView.Adapter currentAdapter = newsList.getAdapter();
        if (currentAdapter != null)
            currentAdapter.notifyDataSetChanged();
        isFABaddsShow = false;
        fabAddsShow(isFABaddsShow);

    }

    public void setListener(NewsAdapter.IDetailNewsListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add:
                fabAddsShow(isFABaddsShow);
                break;
            case R.id.fab_add_workout:
                showDialogWorkoutAdd();
                break;
            case R.id.fab_add_food:

                break;
            case R.id.fab_add_new_sizes:

                break;
        }
    }
    private void showDialogWorkoutAdd() {
        final Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        dialog.setContentView(R.layout.dialog_workouk_add);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        final EditText newTitle = dialog.findViewById(R.id.etNewTitle);
        final EditText newShort = dialog.findViewById(R.id.etNewShort);
        final EditText newLong = dialog.findViewById(R.id.etNewLong);

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
                String titleN = newTitle.getText().toString();
                String shortN = newShort.getText().toString();
                String longN = newLong.getText().toString();
                News newsToAdd = new News(titleN, shortN, longN, R.drawable.pic_time_to_workout);
                NewsManager.getAllNews().add(newsToAdd);
                dialog.dismiss();
                onResume();
            }
        });
        dialog.show();
    }
}
