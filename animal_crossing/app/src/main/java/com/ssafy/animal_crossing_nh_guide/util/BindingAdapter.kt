package com.ssafy.smartstore_jetpack.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass

private const val TAG = "BindingAdapter_싸피"

@BindingAdapter("imageUrl")
fun bindImage(view: ImageView, src: String) {
    Glide.with(view)
        .load("${ApplicationClass.IMGS_URL}${src}")
        .into(view)
}