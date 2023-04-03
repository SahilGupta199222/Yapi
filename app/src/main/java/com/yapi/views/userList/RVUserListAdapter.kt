package com.yapi.views.userList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.yapi.R

class RVUserListAdapter(
    private var context: Context,
    private var userList: ArrayList<UserDataList>, private var userClick: UserClickEvent,
) : RecyclerView.Adapter<RVUserListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rv_list_adpater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (userList[position].isCheck!!) {
            holder.ivPlusIcon.setImageResource(R.drawable.minus_user_list)
            holder.constarintDetails.visibility = View.VISIBLE
            holder.constraintsUsers.setBackgroundColor(context.getColor(R.color.user_list_box))
            holder.viewBottomLine.visibility = View.GONE
            holder.constarintsTopList.setBackgroundResource(R.drawable.et_drawable)
        } else {
            holder.ivPlusIcon.setImageResource(R.drawable.add_user_button)
            holder.constarintDetails.visibility = View.GONE
            holder.constraintsUsers.setBackgroundColor(context.getColor(R.color.white))
            holder.viewBottomLine.visibility = View.VISIBLE
            holder.constarintsTopList.setBackgroundResource(0)
        }
        if (position != userList.size - 1) {
            if (userList[position + 1].isCheck!!) {
                holder.viewBottomLine.visibility = View.GONE
            } else {
                // holder.viewBottomLine.visibility = View.VISIBLE
            }
        }
        holder.ivMorUsers.setOnClickListener {
            userClick.onClick(position, holder.ivMorUsers)
        }

        holder.ivPlusIcon.setOnClickListener {
            userList[position].isCheck = !userList[position].isCheck!!
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var constarintsTopList = itemView.findViewById<ConstraintLayout>(R.id.constarintsTopList)
        var ivPlusIcon = itemView.findViewById<AppCompatImageView>(R.id.ivPlusIcon)
        var constarintDetails = itemView.findViewById<ConstraintLayout>(R.id.constarintDetails)
        var constraintsUsers = itemView.findViewById<ConstraintLayout>(R.id.constraintsUsers)
        var viewBottomLine = itemView.findViewById<View>(R.id.viewBottomLine)
        var ivMorUsers = itemView.findViewById<AppCompatImageView>(R.id.ivMorUsers)
    }

}


interface UserClickEvent {
    fun onClick(position: Int, imageview: AppCompatImageView)
}