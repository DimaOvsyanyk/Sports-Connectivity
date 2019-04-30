package com.dimaoprog.sportsconnectivity.dbRepos;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;
import com.dimaoprog.sportsconnectivity.dbEntities.Meal;
import com.dimaoprog.sportsconnectivity.dbEntities.User;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class FoodRepository {

    private DailyMenuDao dailyMenuDao;
    private MealDao mealDao;

    private LiveData<List<DailyMenu>> menu;
    private LiveData<List<Meal>> meals;

    public static final int WEEK_MENU = R.raw.week_menu;

    public FoodRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        dailyMenuDao = db.dailyMenuDao();
        mealDao = db.mealDao();

        menu = dailyMenuDao.getDailyMenuByUserId(User.getACTIVEUSER().getId());
    }

    public long insert(DailyMenu dailyMenu) {
        return dailyMenuDao.insert(dailyMenu);
    }

    public void update(DailyMenu dailyMenu) {
        dailyMenuDao.update(dailyMenu);
    }

    public void delete(DailyMenu dailyMenu) {
        dailyMenuDao.delete(dailyMenu);
    }

    public DailyMenu getDailyMenuById(long id) {
        return dailyMenuDao.getMenuById(id);
    }

    public LiveData<List<DailyMenu>> getMenu() {
        return menu;
    }

    public void insert(Meal meal) {
        mealDao.insert(meal);
    }

    public void update(Meal meal) {
        mealDao.update(meal);
    }

    public void delete(Meal meal) {
        mealDao.delete(meal);
    }

    public LiveData<List<Meal>> getMeals(long dailyMenuId) {
        meals = mealDao.getMealsByDailyMenuId(dailyMenuId);
        return meals;
    }

    public void addMenuFromJson(Context context) throws IOException, JSONException {
        JSONReader.setMenuFromJSON(context, dailyMenuDao, mealDao, WEEK_MENU);
    }
}
