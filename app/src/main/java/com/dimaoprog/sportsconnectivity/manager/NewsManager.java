package com.dimaoprog.sportsconnectivity.manager;

import android.content.Context;
import com.dimaoprog.sportsconnectivity.news.JSONReader;
import com.dimaoprog.sportsconnectivity.news.News;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsManager {
    
    public static final int NEWS_WORKOUT_FLAG = 0;
    public static final int NEWS_FOOD_FLAG = 1;
    public static final int NEWS_MEASUREMENTS_FLAG = 2;
    public static final int NEWS_WATER_FLAG = 3;
    public static final String CARD_ID = "card id";
    private static List<News> allNews = new ArrayList<>();

    public static List<News> getAllNews() {
        return allNews;
    }

    public static void setAllNews(Context context, int fileId) throws IOException, JSONException {
        NewsManager.allNews = JSONReader.getNewsFromJSON(context, fileId);
    }

}