package com.yapi.views.signupTeam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yapi.R
import com.yapi.databinding.RvUsersAdapterBinding

class RVUsersAdapter(private var context: Context) :
    RecyclerView.Adapter<RVUsersAdapter.MyViewHolder>() {
    private lateinit var binding: RvUsersAdapterBinding

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvFirstCharacter=itemView.findViewById<TextView>(R.id.tvFirstCharacter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       //  binding = RvUsersAdapterBinding.inflate(LayoutInflater.from(context))
        var view=LayoutInflater.from(context).inflate(R.layout.rv_users_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}

