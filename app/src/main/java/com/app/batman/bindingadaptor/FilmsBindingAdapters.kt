package com.app.batman.bindingadaptor

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.batman.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object FilmsBindingAdapters {

    @BindingAdapter("loadImageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {
        url?.let {
            val req =
                RequestOptions()
                    .placeholder(R.drawable.error)
                    .error(R.drawable.error).centerCrop()
            Glide.with(imageView.context).setDefaultRequestOptions(req).load(url)
                .into(imageView)
        }
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter("setTextByNullChecking")
    @JvmStatic
    fun setTextByNullChecking(textView: TextView, str: String?) {
        if (!str.isNullOrEmpty()) {
            textView.text = "TotalSeason : $str"
            textView.visibility = View.VISIBLE
        } else
            textView.visibility = View.GONE

    }

}