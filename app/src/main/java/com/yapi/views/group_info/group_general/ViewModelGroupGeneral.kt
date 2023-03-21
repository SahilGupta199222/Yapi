package com.yapi.views.group_info.group_general

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.yapi.R

class ViewModelGroupGeneral : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.layoutDeActiveGroupGnlInfo -> {
              leaveGroupDialog(view.context)
            }
            R.id.layoutDeleteAccountGroupGnlInfo -> {
                deleteGroupDialog(view.context)
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
}