package com.dimaoprog.sportsconnectivity.dbRepos;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.dimaoprog.sportsconnectivity.dbEntities.MealDBFavorite;

import java.util.List;

public class MealDBRepository {

    private MealDBFavoriteDao mealDBFavoriteDao;
    private LiveData<List<MealDBFavorite>> mealDBFavoriteList;

    public MealDBRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mealDBFavoriteDao = db.mealDBFavoriteDao();
        mealDBFavoriteList = mealDBFavoriteDao.getAllMealsDB();
    }

    public LiveData<List<MealDBFavorite>> getMealDBFavoriteList() {
        return mealDBFavoriteList;
    }

    public MealDBFavorite getMealDBFavotireById(long id) {
        return mealDBFavoriteDao.getMealDBbyId(id);
    }

    public void insert(MealDBFavorite mealDBFavorite) {
        mealDBFavoriteDao.insert(mealDBFavorite);
    }

    public void update(MealDBFavorite mealDBFavorite) {
        mealDBFavoriteDao.update(mealDBFavorite);
    }

    public void delete(MealDBFavorite mealDBFavorite) {
        mealDBFavoriteDao.delete(mealDBFavorite);
    }
}
