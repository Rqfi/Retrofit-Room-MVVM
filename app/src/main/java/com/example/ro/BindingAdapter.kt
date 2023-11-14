package com.example.ro

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setHeight")
fun setHeight(view: TextView, result: ResultsItem?) {
    result?.let {
        val formattedHeight = String.format("%.2f m", (result.height?.toDoubleOrNull()?: 0.0) / 100.0)
        view.text = formattedHeight
    }
}