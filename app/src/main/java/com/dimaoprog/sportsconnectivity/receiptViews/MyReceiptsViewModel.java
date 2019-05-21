package com.dimaoprog.sportsconnectivity.receiptViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.MealDBFavorite;
import com.dimaoprog.sportsconnectivity.dbRepos.MealDBRepository;

import java.util.List;

public class MyReceiptsViewModel extends AndroidViewModel {

    private MealDBRepository mealDBRepo;
    private LiveData<List<MealDBFavorite>> mealDBFavoritesList;

    public MyReceiptsViewModel(@NonNull Application application) {
        super(application);
        mealDBRepo = new MealDBRepository(application);

        mealDBFavoritesList = mealDBRepo.getMealDBFavoriteList();
    }

    public LiveData<List<MealDBFavorite>> getMealDBFavoritesList() {
        return mealDBFavoritesList;
    }

    public void deleteReceipt(MealDBFavorite mealDBFavorite) {
        mealDBRepo.delete(mealDBFavorite);
    }
}
