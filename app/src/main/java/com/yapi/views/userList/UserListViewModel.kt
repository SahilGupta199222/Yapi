package com.yapi.views.userList

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.checkDeviceType

class UserListViewModel : ViewModel() {

    var screenWidth:Int?=0
    fun onClick(view: View) {
        when (view.id) {
            R.id.txtuserBack -> {
                if (checkDeviceType()) {

                } else {
                    view.findNavController().popBackStack()
                }
            }
            R.id.linearAddMember->{
                //For Add Member
                showAddMemberMethod(1)
            }
        }
    }

    fun showAddMemberMethod(type:Int) {

        var dividedValue=0.0
        if(checkDeviceType())
        {
            dividedValue=1.3
        }else
        {
            dividedValue=1.05
        }
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.edit_memeber_info_popup)
        dialog.show()

        var linearEditMember = dialog.findViewById<LinearLayout>(R.id.linearEditMember)
        linearEditMember.layoutParams.width = (screenWidth!!.toDouble() / dividedValue).toInt()
        var ivInnerBack = dialog.findViewById<ImageView>(R.id.ivInnerBack)
        var btnCancelTemplate = dialog.findViewById<AppCompatButton>(R.id.btnCancelTemplate)
        var ivOutsideCloseGroup = dialog.findViewById<ImageView>(R.id.ivOutsideCloseGroup)
        var linearTemplateHeader = dialog.findViewById<LinearLayout>(R.id.linearTemplateHeader)
        var ivaddMemberLogo = dialog.findViewById<AppCompatImageView>(R.id.ivaddMemberLogo)

        var tvAddMemberTitle = dialog.findViewById<AppCompatTextView>(R.id.tvAddMemberTitle)
        var tvAddMemberDescription = dialog.findViewById<AppCompatTextView>(R.id.tvAddMemberDescription)
        var btnTemplateSave = dialog.findViewById<AppCompatButton>(R.id.btnTemplateSave)

        if(checkDeviceType())
        {
            ivOutsideCloseGroup.visibility=View.VISIBLE
            ivInnerBack.visibility=View.GONE
            ivaddMemberLogo.visibility=View.VISIBLE
            ivaddMemberLogo.setImageResource(R.drawable.add_member_logo)
            btnTemplateSave.setText(MainActivity.activity!!.get()!!.resources.getString(R.string.add_member_button))

            tvAddMemberTitle.visibility=View.GONE
            tvAddMemberDescription.visibility=View.GONE
        }else
        {
            ivaddMemberLogo.visibility=View.GONE

            ivOutsideCloseGroup.visibility=View.GONE
            ivInnerBack.visibility=View.VISIBLE

            tvAddMemberTitle.visibility=View.VISIBLE
            tvAddMemberDescription.visibility=View.VISIBLE

            tvAddMemberTitle.setText(MainActivity.activity!!.get()!!.resources.getString(R.string.add_member_texttt))
            btnTemplateSave.setText(MainActivity.activity!!.get()!!.resources.getString(R.string.save_text))
        //    tvAddMemberDescription.setText(MainActivity.activity!!.get()!!.resources.getString(R.string.add_member_text))
        }
        linearTemplateHeader.visibility=View.VISIBLE

        ivInnerBack.setOnClickListener {
            dialog.dismiss()
        }
        ivOutsideCloseGroup.setOnClickListener {
            dialog.dismiss()
        }

        btnCancelTemplate.setOnClickListener {
            dialog.dismiss()
        }
    }
}