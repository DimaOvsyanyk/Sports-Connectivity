package com.dimaoprog.sportsconnectivity.workoutViews;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dagger.AppComponentBuild;
import com.dimaoprog.sportsconnectivity.databinding.FragmentWorkoutAddBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

public class WorkoutAddFragment extends Fragment implements WorkoutAddAdapter.AddNewExerciseListener {

    @Inject
    FragmentNaviManager navigation;

    private WorkoutAddViewModel waViewModel;
    private FragmentWorkoutAddBinding binding;
    Calendar calendar;
    WorkoutAddAdapter addAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_workout_add, container, false);
        AppComponentBuild.getComponent().inject(this);

        waViewModel = ViewModelProviders.of(this).get(WorkoutAddViewModel.class);
        binding.setWorkoutAddModel(waViewModel);
        binding.rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));
        addAdapter = new WorkoutAddAdapter(this, waViewModel.getTempExercises());
        binding.rvExercises.setAdapter(addAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                waViewModel.deleteExerciseFromWorkout(addAdapter.getExerciseAtPos(viewHolder.getAdapterPosition()));
                addAdapter.notifyDataSetChanged();
                checkVisibilityAddBTN();
            }
        }).attachToRecyclerView(binding.rvExercises);

        checkVisibilityAddBTN();
        binding.txtPickMuscleGroups.setOnClickListener(__ -> showPickMuscleGroupsDialog());
        binding.txtPickTheDate.setOnClickListener(__ -> showDatePicker());
        binding.btnAddNewExercise.setOnClickListener(__ -> showAddExerciseDialog());
        binding.btnConfirmAdd.setOnClickListener(__ -> {
            if (waViewModel.checkAllEntities()) {
                waViewModel.addWorkoutAndExercisesToDb();
                navigation.showNewFragment(new WorkoutsListFragment());
            } else {
                Toast.makeText(getContext(), "You should fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        calendar = Calendar.getInstance();
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            new DatePickerDialog(getContext(),
                    (view, year1, month1, dayOfMonth1) -> waViewModel.setWorkoutDate(makeDate(year1, month1, dayOfMonth1)),
                    year, month, dayOfMonth).show();
        }
    }

    private Date makeDate(int year, int month, int dayOfMonth) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return calendar.getTime();
    }

    private void showPickMuscleGroupsDialog() {
        final String[] allMuscleGroups = getResources().getStringArray(R.array.pick_muscle_groups);
        final boolean[] selectedMuscleGroups = new boolean[allMuscleGroups.length];
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.pick_muscle_groups);
        builder.setMultiChoiceItems(allMuscleGroups, selectedMuscleGroups,
                (dialog, which, isChecked) -> selectedMuscleGroups[which] = isChecked);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selectedMuscleGroups.length; i++) {
                if (selectedMuscleGroups[i]) {
                    if (sb.length() == 0) {
                        sb.append(allMuscleGroups[i]);
                    } else {
                        sb.append(", ");
                        sb.append(allMuscleGroups[i]);
                    }
                }
            }
            waViewModel.setMuscleGroups(sb.toString());
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    public void checkVisibilityAddBTN() {
        binding.btnAddNewExercise.setVisibility(waViewModel.getTempExercises().size() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showAddExerciseDialog() {
        final Dialog dialogAddExercise = new Dialog(getContext());
        dialogAddExercise.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAddExercise.setContentView(R.layout.dialog_add_exercise);
        dialogAddExercise.setCanceledOnTouchOutside(true);

        final NumberPicker pickerRounds = dialogAddExercise.findViewById(R.id.num_picker_rounds);
        pickerRounds.setMinValue(1);
        pickerRounds.setMaxValue(50);
        final NumberPicker pickerReps = dialogAddExercise.findViewById(R.id.num_picker_reps);
        pickerReps.setMinValue(1);
        pickerReps.setMaxValue(50);
        final EditText newExerciseTitle = dialogAddExercise.findViewById(R.id.et_exercise_to_add);

        Button btnOk = dialogAddExercise.findViewById(R.id.btn_add_dialog);
        btnOk.setOnClickListener(__ -> {
            if (newExerciseTitle.getText().toString().trim().length() > 0) {
                waViewModel.addNewExerciseToWorkout(new Exercise(newExerciseTitle.getText().toString().trim(),
                        pickerRounds.getValue(), pickerReps.getValue()));
                addAdapter.notifyDataSetChanged();
                dialogAddExercise.dismiss();
                checkVisibilityAddBTN();
            }
        });
        dialogAddExercise.show();
    }
}
