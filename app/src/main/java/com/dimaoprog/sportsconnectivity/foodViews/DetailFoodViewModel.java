package com.dimaoprog.sportsconnectivity.foodViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;
import com.dimaoprog.sportsconnectivity.dbEntities.Meal;
import com.dimaoprog.sportsconnectivity.dbRepos.FoodRepository;

import java.util.List;

public class DetailFoodViewModel extends AndroidViewModel {

    private FoodRepository foodRepo;
    private LiveData<List<Meal>> meals;
    private long menuId;

    public DetailFoodViewModel(@NonNull Application application) {
        super(application);
        foodRepo = new FoodRepository(application);
    }

    public LiveData<List<Meal>> getMeals() {
        meals = foodRepo.getMeals(menuId);
        return meals;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public DailyMenu getCurrentMenu() {
        return foodRepo.getDailyMenuById(menuId);
    }
}
