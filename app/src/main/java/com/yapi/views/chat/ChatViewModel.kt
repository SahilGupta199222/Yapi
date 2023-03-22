package com.yapi.views.chat

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.databinding.ChatAttachementLayoutBinding

class ChatViewModel : ViewModel() {

    init {

    }

    var screenWidth: Int? = 0
    var userType:String?=""
    fun onClick(view: View) {
        when (view.id) {
            R.id.ivChat_more_icon -> {
                // showAddTemplateDialog()
                // attachmentPopupDialog()
                // addLinkDialog()
                showChatMenuMethod(view)
            }
            R.id.tvName->{
                //For Goto Information of user
                var bundle=Bundle()
                bundle.putString("userType",userType.toString())
                view.findNavController().navigate(R.id.action_chatMessageFragment_to_chatUserProfileInfo,bundle)
            }
        }
    }


    //For show Template Dialog
    fun showAddTemplateDialog() {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.add_template_layout)

        dialog.show()
        var cardviewDeleteProfile = dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        cardviewDeleteProfile.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        var btnCancelTemplate = dialog.findViewById<AppCompatButton>(R.id.btnCancelTemplate)
        btnCancelTemplate.setOnClickListener {
            dialog.dismiss()
        }
    }

    //For attachment dialog
    fun attachmentPopupDialog() {
        var bottomSheetDialog =
            BottomSheetDialog(MainActivity.activity!!.get()!!, R.style.CustomBottomSheetDialogTheme)
        //bottomSheetDialog.window.setBackground(new ColorDrawable(Color.TRANSPARENT));
        var chatAttachBinding =
            ChatAttachementLayoutBinding.inflate(LayoutInflater.from(MainActivity.activity!!.get()!!))
        bottomSheetDialog.setContentView(chatAttachBinding.root)
        bottomSheetDialog.show()
    }

    //For Add Link Dialog
    private fun addLinkDialog() {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.add_chat_link_layout)

        dialog.show()
        var cardviewAddLink = dialog.findViewById<CardView>(R.id.cardviewAddLink)
        cardviewAddLink.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        var btnCancelAddLink = dialog.findViewById<AppCompatButton>(R.id.btnCancelAddLink)
        btnCancelAddLink.setOnClickListener {
            dialog.dismiss()
        }
    }

    //When click on the three dots
    fun showChatMenuMethod(view: View) {
        val mView: View = LayoutInflater.from(MainActivity.activity!!.get())
            .inflate(com.yapi.R.layout.chat_menu_options, null, false)
        var newWidth = screenWidth!! / 1.5

        //   val popUp = PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false)
        val popUp = PopupWindow(mView,
            newWidth.toInt(),
            LinearLayout.LayoutParams.WRAP_CONTENT,
            false)
        // popUp.showAtLocation(mView, Gravity.RIGHT,0,0);
        popUp.isTouchable = true
        popUp.isFocusable = true
        popUp.isOutsideTouchable = true
        val btnViewProfile =

            popUp.showAsDropDown(view.findViewById(com.yapi.R.id.ivChat_more_icon))

        var constraintsProfileChat =
            mView.findViewById<ConstraintLayout>(R.id.constraintsProfileChat)
        constraintsProfileChat.setOnClickListener {
            popUp.dismiss()
            if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
                view.findNavController()
                    .navigate(R.id.action_menuFragment_to_profileFragment)
            }
        }
        var constraintsMute =
            mView.findViewById<ConstraintLayout>(R.id.constraintsMute)
        constraintsMute.setOnClickListener {
            popUp.dismiss()
            // showDeleteGroupDialog()
        }
        var constraintsDeleteChat = mView.findViewById<ConstraintLayout>(R.id.constraintsDeleteChat)
        constraintsDeleteChat.setOnClickListener {
            popUp.dismiss()
            if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
                view.findNavController()
                    .navigate(R.id.action_menuFragment_to_chatMessageFragment)
            }
        }
    }
}