package com.rehan.epifiandroidassignment.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
fun loadImage(view: ImageView, url: String?) {

    // Sometimes url image may comes null from server. Inorder to avoid null pointer exception we will use let scope function
    url?.let{
        Glide.with(view).load(url).into(view)
    }

}