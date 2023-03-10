package com.yapi.views.menu_screen

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yapi.R

class AdapterGroupsList(val context: Context,var groupList:Boolean ,var list:ArrayList<PojoGroupMembersList>,val click:Click):RecyclerView.Adapter<AdapterGroupsList.MyViewHolder>() {
class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
    val selectedLine:View=view.findViewById(R.id.SelectedLineRvGroupList)
    val title:AppCompatTextView=view.findViewById(R.id.txtGroupNameRvGroupList)
    val count:AppCompatTextView=view.findViewById(R.id.txtCountRvGroupList)
    val icon:AppCompatImageView=view.findViewById(R.id.imgGroupIconRvGroupList)
}
    fun getListt():ArrayList<PojoGroupMembersList>
    {
        return list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.rv_group_list,parent,false)
        return MyViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.icon.setImageDrawable(ContextCompat.getDrawable(context,android.R.color.transparent))
        if(groupList){
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.group_icon))
        }else{
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.job_icon))
        }
        holder.title.text=list[holder.adapterPosition].name
        if(list[position].unSeenMsgCount>0) {
            holder.count.visibility=View.VISIBLE
            holder.count.text = list[holder.adapterPosition].unSeenMsgCount.toString()
            holder.icon.setColorFilter(ContextCompat.getColor(context,R.color.darkGrey))
            holder.title.setTextColor(ContextCompat.getColor(context,R.color.darkGrey))
        }
        else{
            holder.count.visibility=View.GONE
            holder.icon.setColorFilter(ContextCompat.getColor(context,R.color.medium_grey_color))
            holder.title.setTextColor(ContextCompat.getColor(context,R.color.medium_grey_color))

        }
        if(list[position].selected){
            holder.selectedLine.visibility=View.VISIBLE
            holder.icon.setColorFilter(ContextCompat.getColor(context,R.color.blueColor))
            holder.title.setTextColor(ContextCompat.getColor(context,R.color.blueColor))
        }
        else {
            holder.selectedLine.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener {
//            for(i in 0 until list.size){
//                if(holder.adapterPosition==i){
//                     list[holder.adapterPosition].selected=!list[position].selected
//                }
//                else{
//                    list[holder.adapterPosition].selected=false
//                }
//            }
//            list[holder.adapterPosition].selected=!list[position].selected
//            notifyDataSetChanged()
            click.onSeletect(holder.adapterPosition)
        }
        }

    override fun getItemCount(): Int {
        return list.size
    }
    interface Click{
        fun onSeletect(position: Int)
    }
}