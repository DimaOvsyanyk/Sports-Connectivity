package com.dimaoprog.sportsconnectivity.manager;

import com.dimaoprog.sportsconnectivity.news.News;
import java.util.ArrayList;
import java.util.List;

public class NewsManager {

    public static final String CARD_ID = "card id";

    private static List<News> allNews = new ArrayList<>();

    public static List<News> getAllNews() {
        return allNews;
    }

    public static void setAllNews(List<News> allNews) {
        NewsManager.allNews = allNews;
    }

}