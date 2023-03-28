package com.yapi.views.chat.chatUserInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.yapi.R
import com.yapi.common.RoundRectCornerImageView

class RVPhotoMediaAdapter(
    private var context: Context,
    private var finalPerPhoto: Int,
) : RecyclerView.Adapter<RVPhotoMediaAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivPhotoImageView: RoundRectCornerImageView =
            view.findViewById<RoundRectCornerImageView>(R.id.ivPhotoImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.rv_photo_media_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.ivPhotoImageView.layoutParams.width = finalPerPhoto
        holder.ivPhotoImageView.layoutParams.height = finalPerPhoto
        holder.ivPhotoImageView.requestLayout()
    }

    override fun getItemCount(): Int {
        return 12
    }
}