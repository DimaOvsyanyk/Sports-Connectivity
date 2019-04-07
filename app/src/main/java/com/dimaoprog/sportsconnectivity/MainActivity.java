package com.dimaoprog.sportsconnectivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.dimaoprog.sportsconnectivity.manager.NewsManager;
import com.dimaoprog.sportsconnectivity.news.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.IDetailNewsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewsManager.setAllNews(importAllNews());
        openNewsListFragment();
    }

    private void openNewsListFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fr, NewsListFragment.newInstance(this))
                .commit();
    }

    @Override
    public void openDetailNewsFragment(int i) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.container_fr, DetailNewsFragment.newInstance(i))
                .addToBackStack(null)
                .commit();
    }

    public List<News> importAllNews() {
        List<News> allNews = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.titles);
        String[] shortNews = getResources().getStringArray(R.array.shortNews);
        String[] longNews = getResources().getStringArray(R.array.longNews);
        int[] pictures = {R.drawable.pic_1,
                R.drawable.pic_2,
                R.drawable.pic_3,
                R.drawable.pic_4,
                R.drawable.pic_5,
                R.drawable.pic_6,
                R.drawable.pic_7,
                R.drawable.pic_8,
                R.drawable.pic_9,
                R.drawable.pic_10,
        };
        for (int i = 0; i < titles.length; i++) {
            allNews.add(new News(titles[i], shortNews[i], longNews[i], pictures[i]));
        }
        return allNews;
    }


}
