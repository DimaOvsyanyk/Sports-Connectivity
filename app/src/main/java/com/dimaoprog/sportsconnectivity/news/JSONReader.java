package com.dimaoprog.sportsconnectivity.news;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {
    private static final String TAG_NEWS = "news";
    private static final String TAG_NEWS_FLAG = "flag";
    private static final String TAG_SHORT_DESCRIPTION = "short_description";

    public static List<News> getNewsFromJSON(Context context, int fileId) throws IOException, JSONException {
        List<News> allNews = new ArrayList<>();

        String jsonText = readJSON(context, fileId);

        JSONObject jsonObject = new JSONObject(jsonText);
        JSONArray jsonArrayNews = jsonObject.getJSONArray(TAG_NEWS);
        int flag;
        String description;
        for (int i = 0; i < jsonArrayNews.length(); i++) {
            JSONObject someObject = jsonArrayNews.getJSONObject(i);
            flag = someObject.getInt(TAG_NEWS_FLAG);
            description = someObject.getString(TAG_SHORT_DESCRIPTION);
            News someNews = new News(flag, description);
            allNews.add(someNews);
        }
        return allNews;
    }


    private static String readJSON(Context context, int fileId) throws IOException {
        InputStream input = context.getResources().openRawResource(fileId);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String jsonString;
        while ((jsonString = buffer.readLine()) != null) {
            builder.append(jsonString);
            builder.append("\n");
        }
        return builder.toString();
    }

}
