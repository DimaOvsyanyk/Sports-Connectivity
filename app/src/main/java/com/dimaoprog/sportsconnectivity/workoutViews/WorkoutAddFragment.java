package com.dimaoprog.sportsconnectivity.workoutViews;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.Exercise;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbEntities.Workout;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WorkoutAddFragment extends Fragment implements WorkoutAddAdapter.AddNewExerciseListener {

    public static WorkoutAddFragment newInstance(AddWorkoutListener addWorkoutListener) {
        WorkoutAddFragment fragment = new WorkoutAddFragment();
        fragment.setAddWorkoutListener(addWorkoutListener);
        return fragment;
    }

    private AddWorkoutListener addWorkoutListener;

    public interface AddWorkoutListener {
        void openWorkoutsListFragment();
    }

    public void setAddWorkoutListener(AddWorkoutListener addWorkoutListener) {
        this.addWorkoutListener = addWorkoutListener;
    }

    Unbinder unbinder;

    @BindView(R.id.et_add_workout_title)
    EditText workoutTitle;
    @BindView(R.id.txt_pick_the_date)
    TextView pickTheDate;
    @BindView(R.id.txt_pick_muscle_groups)
    TextView pickMuscleGroup;
    @BindView(R.id.rv_exercises)
    RecyclerView rvAddExercises;
    @BindView(R.id.btn_add_new_exercise)
    Button btnAddNewExercise;

    private WorkoutAddViewModel waViewModel;

    private boolean datePicked;
    private boolean muscleGroupsPicked;
    public static final long TEMP_WORKOUT_ID = 0;
    Calendar calendar;
    WorkoutAddAdapter addAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout_add, container, false);
        unbinder = ButterKnife.bind(this, v);

        waViewModel = ViewModelProviders.of(this).get(WorkoutAddViewModel.class);
        rvAddExercises.setLayoutManager(new LinearLayoutManager(getContext()));
        addAdapter = new WorkoutAddAdapter(this, waViewModel.getTempExercises());
        rvAddExercises.setAdapter(addAdapter);


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
        }).attachToRecyclerView(rvAddExercises);
        checkVisibilityAddBTN();
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        datePicked = false;
        muscleGroupsPicked = false;
        calendar = Calendar.getInstance();
    }

    @OnClick({R.id.txt_pick_the_date, R.id.txt_pick_muscle_groups, R.id.btn_confirm_add, R.id.btn_add_new_exercise})
    void chooseDate(View v) {
        switch (v.getId()) {
            case R.id.txt_pick_the_date:
                showDatePicker();
                break;
            case R.id.txt_pick_muscle_groups:
                showPickMuscleGroupsDialog();
                break;
            case R.id.btn_confirm_add:
                if (checkAllEntities()) {
                    Workout newWorkout = new Workout(User.getACTIVEUSER().getId(), workoutTitle.getText().toString(),
                            pickMuscleGroup.getText().toString(), pickTheDate.getText().toString());
                    long newWorkoutId = waViewModel.insertWorkout(newWorkout);
                    Exercise newExercise;
                    for (int i = 0; i < waViewModel.getTempExercises().size(); i++) {
                        newExercise = waViewModel.getTempExercises().get(i);
                        newExercise.setWorkoutId(newWorkoutId);
                        waViewModel.insertExercise(newExercise);
                    }
                    addWorkoutListener.openWorkoutsListFragment();
                } else {
                    Toast.makeText(getContext(), "You should fill all fields", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_add_new_exercise:
                showAddExerciseDialog();
                break;
        }
    }

    private boolean checkAllEntities() {
        return datePicked & muscleGroupsPicked & workoutTitle.length() > 0 & waViewModel.getTempExercises().size() > 0;
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            new DatePickerDialog(getContext(), onDateSetListener,
                    year, month, dayOfMonth).show();
        }
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String date = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
            pickTheDate.setText(date);
            datePicked = true;
        }
    };

    private void showPickMuscleGroupsDialog() {
        final String[] allMuscleGroups = getResources().getStringArray(R.array.pick_muscle_groups);
        final boolean[] selectedMuscleGroups = new boolean[allMuscleGroups.length];
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.pick_muscle_groups);
        builder.setMultiChoiceItems(allMuscleGroups, selectedMuscleGroups, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                selectedMuscleGroups[which] = isChecked;
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
                pickMuscleGroup.setText(sb.toString());
                muscleGroupsPicked = true;
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    public void checkVisibilityAddBTN() {
        btnAddNewExercise.setVisibility(waViewModel.getTempExercises().size() > 0 ? View.GONE : View.VISIBLE);
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
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newExerciseTitle.getText().toString().trim().length() > 0) {
                    waViewModel.addNewExerciseToWorkout(new Exercise(TEMP_WORKOUT_ID, newExerciseTitle.getText().toString().trim(),
                            pickerRounds.getValue(), pickerReps.getValue()));
                    addAdapter.notifyDataSetChanged();
                    dialogAddExercise.dismiss();
                    checkVisibilityAddBTN();
                }
            }
        });
        dialogAddExercise.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
