package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;

import java.util.List;

public class DoExercisePagerAdapter extends FragmentPagerAdapter {

    private List<Exercise> exercisesToDo;

    public DoExercisePagerAdapter(FragmentManager fm, List<Exercise> exercisesToDo) {
        super(fm);
        this.exercisesToDo = exercisesToDo;
    }

    @Override
    public Fragment getItem(int position) {
        return DoExercisesFragment.newInstance(exercisesToDo.get(position).getWorkoutId(), position);
    }

    @Override
    public int getCount() {
        return exercisesToDo.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return exercisesToDo.get(position).getExerciseTitle();
    }
}
