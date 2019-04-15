package com.dimaoprog.sportsconnectivity.news;

import com.dimaoprog.sportsconnectivity.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static com.dimaoprog.sportsconnectivity.manager.NewsManager.NEWS_FOOD_FLAG;
import static com.dimaoprog.sportsconnectivity.manager.NewsManager.NEWS_MEASUREMENTS_FLAG;
import static com.dimaoprog.sportsconnectivity.manager.NewsManager.NEWS_WATER_FLAG;
import static com.dimaoprog.sportsconnectivity.manager.NewsManager.NEWS_WORKOUT_FLAG;

public class News {
    private int flag;
    private int titleResource;
    private String shortDescription;
    private String time;
    private int iconResource;

    public News(int flag, String shortDescription) {
        this.flag = flag;
        this.shortDescription = shortDescription;
        Date currentTime = Calendar.getInstance().getTime();
        setTime(currentTime);
        setTitleResource(flag);
        setIconResource(flag);
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getTitleResource() {
        return titleResource;
    }

    public void setTitleResource( int flag) {
        int title = 0;
        if (flag == NEWS_WORKOUT_FLAG) {
            title = R.string.title_workout;
        } else if (flag == NEWS_FOOD_FLAG) {
            title = R.string.title_food;
        } else if (flag == NEWS_MEASUREMENTS_FLAG) {
            title = R.string.title_new_measurements;
        } else if (flag == NEWS_WATER_FLAG) {
            title = R.string.title_water;
        }
        this.titleResource = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getTime() {
        return time;
    }

    public void setTime(Date time) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        this.time = dateFormat.format(time);
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int flag) {
        int icon = 0;
        if (flag == NEWS_WORKOUT_FLAG) {
            icon = R.drawable.icon_workout;
        } else if (flag == NEWS_FOOD_FLAG) {
            icon = R.drawable.icon_food;
        } else if (flag == NEWS_MEASUREMENTS_FLAG) {
            icon = R.drawable.icon_measurements;
        } else if (flag == NEWS_WATER_FLAG) {
            icon = R.drawable.icon_water;
        }
        this.iconResource = icon;
    }
}
