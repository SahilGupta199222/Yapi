package com.yapi.views.group_info.add_group_members

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.yapi.R

class AdapterGroupMembers(val context: Context,val list:ArrayList<PojoGroupMembers>):RecyclerView.Adapter<AdapterGroupMembers.MyViewHolder>() {
class MyViewHolder(val itemView:View):RecyclerView.ViewHolder(itemView){
    val name=itemView.findViewById<AppCompatTextView>(R.id.txtUserNameRvMemberListGroup)
    val designation=itemView.findViewById<AppCompatTextView>(R.id.txtUserDesignationRvMemberListGroup)
    val txtUserPositionRvMenerListCreateGroup=itemView.findViewById<AppCompatTextView>(R.id.txtUserPositionRvMenerListCreateGroup)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.rv_member_list_group,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text=list[position].name
        holder.designation.text=list[position].designation

      /*  if(position==0 || position==2)
        {
            holder.txtUserPositionRvMenerListCreateGroup.setText("Owner")
        }else
        {*/
            holder.txtUserPositionRvMenerListCreateGroup.setText("Manager")
       // }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}