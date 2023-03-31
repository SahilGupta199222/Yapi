package com.yapi.views.search_result

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.Hold
import com.yapi.R

class AdapterTabSearchResult(val context:Context, var list:ArrayList<PojoTabSearchResult>,val click:Click):RecyclerView.Adapter<AdapterTabSearchResult.MyViewHolder>() {
class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val title:AppCompatTextView=itemView.findViewById(R.id.txtTitleTabSearchResult)
    val notificationCount:AppCompatTextView=itemView.findViewById(R.id.txtCountTabSearchResult)
    val viewLine:View=itemView.findViewById(R.id.viewLineTabSearchResult)
}
    fun changeList( updateList: ArrayList<PojoTabSearchResult>){
        this.list=updateList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.rv_tab_search_result,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text=list[position].title
        holder.notificationCount.text=list[position].notificationCount.toString()
        holder.viewLine.setBackgroundColor(ContextCompat.getColor(context,R.color.liteGrey))

         if(list[position].selected ==0){
            holder.notificationCount.setTextColor(ContextCompat.getColor(context,R.color.white))
            holder.title.setTextColor(ContextCompat.getColor(context,R.color.blueColor))
             holder.viewLine.setBackgroundColor(ContextCompat.getColor(context,R.color.blueColor))
         }
        else if(list[position].selected == 1){

            holder.notificationCount.setTextColor(ContextCompat.getColor(context,R.color.white))
            holder.title.setTextColor(ContextCompat.getColor(context,R.color.darkGrey))
        }else{
            Log.i("sdfbasf","else part")
             val colorStateList =
                 ColorStateList(arrayOf(intArrayOf(android.R.attr.state_pressed),
                     intArrayOf(android.R.attr.state_focused),
                     intArrayOf(android.R.attr.state_enabled),
                     intArrayOf()), intArrayOf(
                     R.color.chat_bottom_line_color,  // color for pressed state
                     R.color.chat_bottom_line_color,  // color for focused state
                     R.color.chat_bottom_line_color,  // color for enabled state
                     R.color.chat_bottom_line_color // color for default state
                 ))
             holder.notificationCount.backgroundTintList=colorStateList
             holder.notificationCount.setTextColor(ContextCompat.getColor(context,R.color.darkLiteGrey))
             holder.title.setTextColor(ContextCompat.getColor(context,R.color.darkGrey))
        }
        holder.itemView.setOnClickListener {
            click.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface Click{
        fun onClick(position:Int)
    }
}