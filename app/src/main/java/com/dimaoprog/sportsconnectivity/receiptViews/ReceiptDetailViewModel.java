package com.dimaoprog.sportsconnectivity.receiptViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dimaoprog.sportsconnectivity.dagger.DaggerAppComponent;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDB;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDBFavorite;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDBResponse;
import com.dimaoprog.sportsconnectivity.dbRepos.MealDBRepository;
import com.dimaoprog.sportsconnectivity.retrofit.MealDBRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dimaoprog.sportsconnectivity.Constants.LOG_MAIN;

public class ReceiptDetailViewModel extends AndroidViewModel {

    private MealDB currentMealDB;
    private MutableLiveData<MealDB> currentMealDBLive = new MutableLiveData<>();
    private MealDBRepository mealDBRepo;

    public ReceiptDetailViewModel(@NonNull Application application) {
        super(application);
        mealDBRepo = new MealDBRepository(application);
    }

    public void setCurrentMealById(String id) {
        DaggerAppComponent.create().getMealDBApi().getMealById(id).enqueue(new Callback<MealDBResponse>() {
            @Override
            public void onResponse(Call<MealDBResponse> call, Response<MealDBResponse> response) {
                setCurrentMealDB(response.body().getMealsDB().get(0));
            }

            @Override
            public void onFailure(Call<MealDBResponse> call, Throwable t) {

            }
        });
    }

    public MealDB getCurrentMealDB() {
        return currentMealDB;
    }

    public void setCurrentMealDB(MealDB currentMealDB) {
        this.currentMealDB = currentMealDB;
        setCurrentMealDBLive();
    }

    public MutableLiveData<MealDB> getCurrentMealDBLive() {
        return currentMealDBLive;
    }

    public void setCurrentMealDBLive() {
        currentMealDBLive.setValue(currentMealDB);
    }

    public boolean saveMealDBtoFavorite() {
        MealDBFavorite NewMealDBFavorite = new MealDBFavorite(currentMealDB);
        if (mealDBRepo.getMealDBFavotireById(NewMealDBFavorite.getIdMeal()) != null) {
            return false;
        } else {
            mealDBRepo.insert(NewMealDBFavorite);
            return true;
        }
    }
}
