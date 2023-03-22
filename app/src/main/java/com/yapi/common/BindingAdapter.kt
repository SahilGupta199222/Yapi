package com.yapi.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapter {

    @BindingAdapter(value = ["setRecyclerAdapter"], requireAll = false)
    @JvmStatic
    fun setRecyclerAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<*>,
    ) {
        recyclerView.adapter = adapter
    }
}