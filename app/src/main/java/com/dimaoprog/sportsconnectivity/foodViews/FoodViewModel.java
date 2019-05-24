package com.dimaoprog.sportsconnectivity.foodViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dbEntities.DailyMenu;
import com.dimaoprog.sportsconnectivity.dbEntities.Meal;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbRepos.FoodRepository;

import org.json.JSONException;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FoodViewModel extends AndroidViewModel{

    private FoodRepository foodRep;
    private LiveData<List<DailyMenu>> allMenu;

    private String breakfast;
    private String snackFirst;
    private String lunch;
    private String snackSecond;
    private String dinner;

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

    public boolean isMenuOk() {
        return breakfast.length() > 3 &
                snackFirst.length() > 3 &
                lunch.length() > 3 &
                snackSecond.length() > 3 &
                dinner.length() > 3;
    }

    public void addNewMenu(String[] foodIntakes, String[] daysOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(foodRep.getLastMenu().getDateOfMenu());
        int dayOfLastMenu = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfLastMenu + 1);
        int dayOfWeekNew = calendar.get(Calendar.DAY_OF_WEEK);
        String[] meals = new String[] {breakfast, snackFirst, lunch, snackSecond, dinner};

        long menuId = foodRep.insert(new DailyMenu(User.getACTIVEUSER().getId(),
                daysOfWeek[dayOfWeekNew], calendar.getTime()));
        for (int i = 0; i < foodIntakes.length; i++) {
            foodRep.insert(new Meal(menuId, foodIntakes[i], meals[i]));
        }

    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast.trim();
    }

    public String getSnackFirst() {
        return snackFirst;
    }

    public void setSnackFirst(String snackFirst) {
        this.snackFirst = snackFirst.trim();
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch.trim();
    }

    public String getSnackSecond() {
        return snackSecond;
    }

    public void setSnackSecond(String snackSecond) {
        this.snackSecond = snackSecond.trim();
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner.trim();
    }
}
