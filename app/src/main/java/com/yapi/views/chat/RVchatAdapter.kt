package com.yapi.views.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yapi.R

class RVchatAdapter(private var context: Context) :
    RecyclerView.Adapter<RVchatAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view: View? = null
        if (viewType==1) {
            view = LayoutInflater.from(context).inflate(R.layout.left_chat_layout, parent, false)
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.right_chat_layout, parent, false)
        }
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }
    override fun getItemViewType(position: Int): Int {
        return if (position%2==0) {
            1
        } else {
            2
        }
    }
}