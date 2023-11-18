package com.ssafy.smartstore_jetpack.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass

private const val TAG = "BindingAdapter_싸피"

@BindingAdapter("imageUrl")
fun bindImage(view: ImageView, src: String) {
    Glide.with(view)
        .load("${ApplicationClass.IMGS_URL}${src}")
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
        .into(view)
}