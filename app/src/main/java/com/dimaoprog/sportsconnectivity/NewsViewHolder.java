package com.dimaoprog.sportsconnectivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.dimaoprog.sportsconnectivity.news.News;


public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle, txtShortText, txtTime;
    private ImageView newsPicture;
    private Button btnGoDetail;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtShortText = itemView.findViewById(R.id.txtShortText);
        txtTime = itemView.findViewById(R.id.txtTime);
        newsPicture = itemView.findViewById(R.id.imageView);
        btnGoDetail = itemView.findViewById(R.id.btn_go_detail);
        txtShortText.setVisibility(View.GONE);
        btnGoDetail.setVisibility(View.GONE);
    }

    public void bind(News news, final int i, final NewsAdapter.IDetailNewsListener listener) {
        txtTitle.setText(news.getTitleResource());
        txtShortText.setText(news.getShortDescription());
        newsPicture.setImageResource(news.getIconResource());
        txtTime.setText(news.getTime());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtShortText.getVisibility() == View.GONE) {
                    txtShortText.setVisibility(View.VISIBLE);
                    btnGoDetail.setVisibility(View.VISIBLE);
                } else {
                    txtShortText.setVisibility(View.GONE);
                    btnGoDetail.setVisibility(View.GONE);
                }
            }
        });
        btnGoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.openDetailNewsFragment(i);
            }
        });
    }
}
