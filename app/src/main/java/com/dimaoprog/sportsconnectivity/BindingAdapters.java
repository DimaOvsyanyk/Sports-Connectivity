package com.dimaoprog.sportsconnectivity;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter({"app:url", "app:errorImage"})
    public static void loadImage(ImageView view, String url, Drawable errorImage) {
        Picasso.get()
                .load(url)
                .error(errorImage)
                .into(view);
    }
}
