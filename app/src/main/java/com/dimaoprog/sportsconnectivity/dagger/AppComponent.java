package com.dimaoprog.sportsconnectivity.dagger;

import com.dimaoprog.sportsconnectivity.foodViews.DetailFoodFragment;
import com.dimaoprog.sportsconnectivity.foodViews.FoodFragment;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.LoginFragment;
import com.dimaoprog.sportsconnectivity.loginRegistrationViews.RegistrationFragment;
import com.dimaoprog.sportsconnectivity.profileViews.ProfileFragment;
import com.dimaoprog.sportsconnectivity.receiptViews.MyReceiptDetailFragment;
import com.dimaoprog.sportsconnectivity.receiptViews.MyReceiptsFragment;
import com.dimaoprog.sportsconnectivity.receiptViews.ReceiptAddNewFragment;
import com.dimaoprog.sportsconnectivity.retrofit.IMealDB;
import com.dimaoprog.sportsconnectivity.retrofit.MealDBRetrofit;
import com.dimaoprog.sportsconnectivity.workoutViews.DetailWorkoutFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutAddFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.WorkoutsListFragment;
import com.dimaoprog.sportsconnectivity.workoutViews.doWorkout.DoWorkoutFragment;

import dagger.Component;

@Component(modules = {MealDBRetrofit.class, NaviModule.class})
public interface AppComponent {
    IMealDB getMealDBApi();

    void inject(ProfileFragment profileFragment);

    void inject(WorkoutsListFragment workoutsListFragment);

    void inject(WorkoutAddFragment workoutAddFragment);

    void inject(DetailWorkoutFragment detailWorkoutFragment);

    void inject(DoWorkoutFragment doWorkoutFragment);

    void inject(ReceiptAddNewFragment receiptAddNewFragment);

    void inject(MyReceiptsFragment myReceiptsFragment);

    void inject(LoginFragment loginFragment);

    void inject(RegistrationFragment registrationFragment);

    void inject(FoodFragment foodFragment);
}
