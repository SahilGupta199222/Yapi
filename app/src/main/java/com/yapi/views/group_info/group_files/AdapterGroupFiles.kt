package com.yapi.views.group_info.group_files

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.yapi.R

class AdapterGroupFiles(val context: Context, val list:ArrayList<PojoGroupFiles>):RecyclerView.Adapter<AdapterGroupFiles.MyViewHolder>() {
class MyViewHolder(val itemView:View):RecyclerView.ViewHolder(itemView){
    val fileName=itemView.findViewById<AppCompatTextView>(R.id.txtFileNameRvGroupFiles)
    val fileSize=itemView.findViewById<AppCompatTextView>(R.id.txtFileSizeRvGroupFiles)
    val fileSharedBy=itemView.findViewById<AppCompatTextView>(R.id.txtFileInfoRvGroupFiles)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.rv_group_files_list,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fileName.text=list[position].fileName
        holder.fileSize.text=list[position].fileSize+"Mb"
        holder.fileSharedBy.text=list[position].fileSharedBy
    }

    override fun getItemCount(): Int {
        return list.size
    }

}