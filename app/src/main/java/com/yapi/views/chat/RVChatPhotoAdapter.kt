package com.yapi.views.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.yapi.R
import com.yapi.common.RoundRectCornerImageView

class RVChatPhotoAdapter(private var context: Context) :
    RecyclerView.Adapter<RVChatPhotoAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPhotoView = itemView.findViewById<RoundRectCornerImageView>(R.id.ivPhotoView)
        var relVideoView = itemView.findViewById<RelativeLayout>(R.id.relVideoView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.chat_photo_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(position==3)
        {
            holder.ivPhotoView.visibility=View.GONE
            holder.relVideoView.visibility=View.VISIBLE
        }else
        {
            holder.ivPhotoView.visibility=View.VISIBLE
            holder.relVideoView.visibility=View.GONE
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}