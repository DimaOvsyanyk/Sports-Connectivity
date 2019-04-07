package com.dimaoprog.sportsconnectivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.dimaoprog.sportsconnectivity.news.News;


public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle, txtShortText;
    private ImageView newsPicture;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtShortText = itemView.findViewById(R.id.txtShortText);
        newsPicture = itemView.findViewById(R.id.imageView);
    }

    public void bind(News news, final int i, final NewsAdapter.IDetailNewsListener listener) {
        txtTitle.setText(news.getTitle());
        txtShortText.setText(news.getShortNew());
        newsPicture.setImageResource(news.getImage());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.openDetailNewsFragment(i);
            }
        });
    }
}
