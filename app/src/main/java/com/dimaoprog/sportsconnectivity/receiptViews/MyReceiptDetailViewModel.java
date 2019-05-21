package com.dimaoprog.sportsconnectivity.receiptViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dimaoprog.sportsconnectivity.dbEntities.MealDB;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDBFavorite;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDBResponse;
import com.dimaoprog.sportsconnectivity.dbRepos.MealDBRepository;
import com.dimaoprog.sportsconnectivity.retrofit.MealDBRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dimaoprog.sportsconnectivity.Constants.LOG_MAIN;

public class MyReceiptDetailViewModel extends AndroidViewModel {

    private MealDBFavorite currentMealDBFavorite;
    private MealDBRepository mealDBRepo;

    public MyReceiptDetailViewModel(@NonNull Application application) {
        super(application);
        mealDBRepo = new MealDBRepository(application);
    }

    public void setCurrentMealDBFavorite(long id) {
        currentMealDBFavorite = mealDBRepo.getMealDBFavotireById(id);
    }

    public MealDBFavorite getCurrentMealDBFavorite() {
        return currentMealDBFavorite;
    }
}
