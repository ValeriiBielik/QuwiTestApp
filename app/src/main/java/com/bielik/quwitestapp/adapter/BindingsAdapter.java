package com.bielik.quwitestapp.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bielik.quwitestapp.R;
import com.bumptech.glide.Glide;

public class BindingsAdapter {

    @BindingAdapter("app:imageURL")
    public static void loadImage(ImageView view, String imageURL) {
        Glide.with(view)
                .load(imageURL)
                .error(R.drawable.ic_no_icon)
                .circleCrop()
                .into(view);
    }
}
