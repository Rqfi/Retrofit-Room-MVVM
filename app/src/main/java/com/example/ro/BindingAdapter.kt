package com.example.ro

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setHeight")
fun setHeight(view: TextView, height: String?) {
    height?.let {
        val formattedHeight = String.format("%.2f m", (height?.toDoubleOrNull()?: 0.0) / 100.0)
        view.text = formattedHeight
    }
}