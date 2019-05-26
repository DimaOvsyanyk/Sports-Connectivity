package com.dimaoprog.sportsconnectivity.receiptViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dagger.AppComponentBuild;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDB;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDBFavorite;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDBResponse;
import com.dimaoprog.sportsconnectivity.dbRepos.MealDBRepository;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiptDetailViewModel extends AndroidViewModel {

    private MealDB currentMealDB;
    private MutableLiveData<MealDB> currentMealDBLive = new MutableLiveData<>();
    private MealDBRepository mealDBRepo;
    private boolean showDialog;
    private MutableLiveData<Boolean> showDialogLive = new MutableLiveData<>();

    public ReceiptDetailViewModel(@NonNull Application application) {
        super(application);
        mealDBRepo = new MealDBRepository(application);
    }

    public void setCurrentMealById(String id) {
        setShowDialog(true);
        AppComponentBuild.getComponent().getMealDBApi().getMealById(id).enqueue(new Callback<MealDBResponse>() {
            @Override
            public void onResponse(@NonNull Call<MealDBResponse> call, @NonNull Response<MealDBResponse> response) {
                if (response.isSuccessful()) {
                    setCurrentMealDB(Objects.requireNonNull(response.body()).getMealsDB().get(0));
                    setShowDialog(false);
                }
            }

            @Override
            public void onFailure(Call<MealDBResponse> call, Throwable t) {

            }
        });
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
        setShowDialogLive();
    }

    public MutableLiveData<Boolean> getShowDialogLive() {
        return showDialogLive;
    }

    public void setShowDialogLive() {
        showDialogLive.setValue(showDialog);
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
