package com.yapi.views.group_info.group_general

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard

class ViewModelGroupGeneral : ViewModel() {
     val groupNameCount=ObservableField<String>("0/128")
     val groupDescriptionNameCount=ObservableField<String>("0/256")
    fun onClick(view: View) {
        when (view.id) {
            R.id.layoutDeActiveGroupGnlInfo -> {
              leaveGroupDialog(view.context)
            }
            R.id.layoutDeleteAccountGroupGnlInfo -> {
                deleteGroupDialog(view.context)
            }
            R.id.layoutUploadImageGroupGnlInfoEdit->{
                //Toast.makeText(view.context, "Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.layoutGroupGnlInfoEdit,R.id.layoutGroupGnlInfo->{
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    private fun leaveGroupDialog(context: Context){
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.leave_module_popup)
        dialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(context,android.R.color.transparent))
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        val crosseIcon:AppCompatImageView = dialog.findViewById(R.id.ivCross)
        val cancelBtn: AppCompatButton = dialog.findViewById(R.id.btnCancel)
        val btnLeave: AppCompatButton = dialog.findViewById(R.id.btnDelGroup)
        crosseIcon.setOnClickListener {
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        btnLeave.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()
    }
    private fun deleteGroupDialog(context: Context){
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.delete_group_popup)
        dialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(context,android.R.color.transparent))
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        val crosseIcon:AppCompatImageView = dialog.findViewById(R.id.ivCross)
        crosseIcon.setOnClickListener {
            dialog.dismiss()
        }
        val cancelBtn: AppCompatButton = dialog.findViewById(R.id.btnCancel)
        val btnLeave: AppCompatButton = dialog.findViewById(R.id.btnDelGroup)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
            }
        btnLeave.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()
    }
    fun onGroupNameTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        groupNameCount.set(s.length.toString() + "/128")
    }
    fun onGroupDescriptionNameTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        groupDescriptionNameCount.set(s.length.toString() + "/256")
    }
}