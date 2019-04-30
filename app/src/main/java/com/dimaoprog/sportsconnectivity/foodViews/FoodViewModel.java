package com.dimaoprog.sportsconnectivity.foodViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;
import com.dimaoprog.sportsconnectivity.dbRepos.FoodRepository;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class FoodViewModel extends AndroidViewModel{

    private FoodRepository foodRep;
    private LiveData<List<DailyMenu>> allMenu;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodRep = new FoodRepository(application);
        allMenu = foodRep.getMenu();
    }

    public LiveData<List<DailyMenu>> getAllMenu() {
        return allMenu;
    }

    public void deleteMenu(DailyMenu dailyMenu) {
        foodRep.delete(dailyMenu);
    }

    public void addWeekMenuFromJson(Context context) throws IOException, JSONException {
        foodRep.addMenuFromJson(context);
    }
}
