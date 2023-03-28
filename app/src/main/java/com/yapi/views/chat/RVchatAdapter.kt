package com.yapi.views.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.yapi.R

class RVchatAdapter(private var context: Context,private var clickListener: MessageClickListener) :
    RecyclerView.Adapter<RVchatAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
      var  iv_more_chat=view.findViewById<ImageView>(R.id.iv_more_chat)
    }

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
        holder.iv_more_chat.setOnClickListener {
            clickListener.onMesssageListener(position,holder.iv_more_chat)
        }
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
interface MessageClickListener
{
    fun onMesssageListener(position: Int,ivMoreImageView:ImageView)
}