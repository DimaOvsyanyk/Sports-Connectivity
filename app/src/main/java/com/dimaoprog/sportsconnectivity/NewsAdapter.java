package com.dimaoprog.sportsconnectivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dimaoprog.sportsconnectivity.manager.NewsManager;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    public interface IDetailNewsListener{
        void openDetailNewsFragment(int i);
    }

    private IDetailNewsListener detailListener;

    public NewsAdapter(IDetailNewsListener detailListener) {
        this.detailListener = detailListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        newsViewHolder.bind(NewsManager.getAllNews().get(i), i, detailListener);
    }

    @Override
    public int getItemCount() {
        return NewsManager.getAllNews().size();
    }
}
