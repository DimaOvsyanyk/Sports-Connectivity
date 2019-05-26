package com.dimaoprog.sportsconnectivity.receiptViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.MealDBFavorite;
import com.dimaoprog.sportsconnectivity.dbRepos.MealDBRepository;

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
