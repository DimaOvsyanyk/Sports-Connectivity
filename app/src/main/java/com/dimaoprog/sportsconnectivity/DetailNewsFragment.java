package com.dimaoprog.sportsconnectivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dimaoprog.sportsconnectivity.manager.NewsManager;
import com.dimaoprog.sportsconnectivity.news.News;

public class DetailNewsFragment extends Fragment {

    public static DetailNewsFragment newInstance(int i) {
        Bundle args = new Bundle();
        args.putInt(NewsManager.CARD_ID, i);
        DetailNewsFragment fragment = new DetailNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    TextView title;
    TextView shortNew;
    TextView longNew;
    ImageView picture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_news, container, false);
        initViews(v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int i = getArguments().getInt(NewsManager.CARD_ID, -1);
        News someNew = NewsManager.getAllNews().get(i);
        fillViews(someNew);
    }

    private void initViews(View v) {
        title = v.findViewById(R.id.txt_title);
        shortNew = v.findViewById(R.id.txt_short);
        longNew = v.findViewById(R.id.txt_long);
        picture = v.findViewById(R.id.img_picture);
    }

    private void fillViews(News someNew) {
        title.setText(someNew.getTitle());
        shortNew.setText(someNew.getShortNew());
        longNew.setText(someNew.getLongNew());
        picture.setImageResource(someNew.getImage());
    }


}
