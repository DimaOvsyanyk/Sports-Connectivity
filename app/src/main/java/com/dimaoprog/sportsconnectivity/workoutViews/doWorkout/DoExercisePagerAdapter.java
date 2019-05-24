package com.dimaoprog.sportsconnectivity.workoutViews.doWorkout;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.ExerciseDone;

import java.util.ArrayList;
import java.util.List;

public class DoExercisePagerAdapter extends FragmentStatePagerAdapter {

    private List<Exercise> exercisesToDo;
    private List<DoExercisesFragment> doExercisesFragmentList = new ArrayList<>();

    private CheckCompleteExerciseDoneListener checkCompleteExerciseDoneListener;

    public interface CheckCompleteExerciseDoneListener {
        void checkCompleteExerciseDone();
    }

    public DoExercisePagerAdapter(FragmentManager fm, List<Exercise> exercisesToDo, CheckCompleteExerciseDoneListener checkCompleteExerciseDoneListener) {
        super(fm);
        this.checkCompleteExerciseDoneListener = checkCompleteExerciseDoneListener;
        this.exercisesToDo = exercisesToDo;
        for (int i = 0; i < exercisesToDo.size(); i++) {
            doExercisesFragmentList.add(DoExercisesFragment.newInstance(exercisesToDo.get(i).getWorkoutId(), i, checkCompleteExerciseDoneListener));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return doExercisesFragmentList.get(position);
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

    public List<List<ExerciseDone>> getAllExercisesDoneLists() {
        List<List<ExerciseDone>> allExercisesDoneLists = new ArrayList<>();
        for (int i = 0; i < doExercisesFragmentList.size(); i++) {
            if (!doExercisesFragmentList.get(i).getBtnAddVisibility()) {
                allExercisesDoneLists.add(doExercisesFragmentList.get(i).getExercisesDoneList());
            }
        }
        return allExercisesDoneLists;
    }

    public boolean isAllExercisesDone() {
        for (int i = 0; i < doExercisesFragmentList.size(); i++) {
            if (doExercisesFragmentList.get(i).getBtnAddVisibility()){
                return false;
            }
        }
        return true;
    }

}
