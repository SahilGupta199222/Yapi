package com.yapi.views.chat

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.*
import com.yapi.databinding.ChatAttachementLayoutBinding
import com.yapi.views.chat.chatUserInfo.RVFilesAdapter
import com.yapi.views.chat.chatUserInfo.RVLinksAdapter
import com.yapi.views.chat.chatUserInfo.RVPhotoMediaAdapter
import org.greenrobot.eventbus.EventBus

class ChatViewModel : ViewModel() {

    var chatValue = ObservableBoolean(false)
    var emailValue = ObservableBoolean(false)
    var smsValue = ObservableBoolean(false)

    var screenWidth: Int? = 0
    var screenHeight: Int? = 0
    var userType: String? = ""
    var sendDataValue = ObservableField("")
    var backButtonVisible=ObservableBoolean(false)
    fun onClick(view: View) {
        when (view.id) {
            R.id.ivChat_more_icon -> {
                // showAddTemplateDialog()
                // attachmentPopupDialog()
                // addLinkDialog()
                //   showChatMenuMethod(view)
                if (userType.equals(Constants.GROUPS_KEY)) {
                    showChatGroupMenuMethod(view)
                } else {
                    showChatMenuMethod(view)
                }
            }
            R.id.tvName -> {
                showViewAllMethod()
                /*  //For Goto Information of user
                  var bundle=Bundle()
                  bundle.putString("userType",userType.toString())
                  if(userType.equals(Constants.GROUPS_KEY)){

                  }
                  view.findNavController().navigate(R.id.action_chatMessageFragment_to_chatUserProfileInfo,bundle)
             */
            }
            R.id.ivChatBack -> {
                //For back pressed Method
                view.findNavController().navigateUp()
            }
            R.id.imgAttachmentIconChatDemo -> {
                //For Attachment
                attachmentPopupDialog()
            }
            R.id.imgLinkIconChatDemo -> {
                //for Add Template
                showAddTemplateDialog()
            }
            R.id.tvMessages -> {
                setDataTabs(1)
            }
            R.id.tvEmail -> {
                setDataTabs(2)
            }

            R.id.tvSMS -> {
                setDataTabs(3)
            }
            R.id.imgSendIconChatDemo -> {
                sendDataValue.set("")
            }
        }
    }

    private fun setDataTabs(tabValue: Int) {
        chatValue.set(false)
        emailValue.set(false)
        smsValue.set(false)
        if (tabValue == 1) {
            chatValue.set(true)
        } else if (tabValue == 2) {
            emailValue.set(true)
        } else {
            smsValue.set(true)
        }
    }

    var keyboardHideData = MutableLiveData<Boolean>()

    //For show Template Dialog
    private fun showAddTemplateDialog() {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.add_template_layout)

        dialog.show()
        var cardviewAddTemplate = dialog.findViewById<CardView>(R.id.cardviewAddTemplate)
        var ivOutsideCloseGroup = dialog.findViewById<ImageView>(R.id.ivOutsideCloseGroup)
        var constraintsTopTemplate =
            dialog.findViewById<ConstraintLayout>(R.id.constraintsTopTemplate)
        var ivTemplateLogo = dialog.findViewById<ImageView>(R.id.ivTemplateLogo)
        cardviewAddTemplate.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        if (checkDeviceType()) {
            ivOutsideCloseGroup.visibility = View.VISIBLE
        } else {
            ivOutsideCloseGroup.visibility = View.GONE
        }

        cardviewAddTemplate.setOnClickListener {
            keyboardHideData.value = true
        }
        constraintsTopTemplate.setOnClickListener {
            keyboardHideData.value = true
        }
        ivTemplateLogo.setOnClickListener {
            keyboardHideData.value = true
        }

        var btnCancelTemplate = dialog.findViewById<AppCompatButton>(R.id.btnCancelTemplate)
        btnCancelTemplate.setOnClickListener {
            dialog.dismiss()
        }
        ivOutsideCloseGroup.setOnClickListener {
            dialog.dismiss()
        }
    }

    //For attachment dialog
    private fun attachmentPopupDialog() {
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
    private fun showChatMenuMethod(view: View) {
        val mView: View = LayoutInflater.from(MainActivity.activity!!.get())
            .inflate(com.yapi.R.layout.chat_menu_options, null, false)
        var newWidth = screenWidth!! / 1.5

        //   val popUp = PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false)
        val popUp =
            PopupWindow(mView, newWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT, false)
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
            if (checkDeviceType()) {
                EventBus.getDefault().post(MyMessageEvent(10, Constants.USER_PROFILE)) //post event
            } else {
                if (view.findNavController().currentDestination?.id == R.id.chatMessageFragment) {
                    var bundle = Bundle()
                    bundle.putString("userType", userType.toString())
                    view.findNavController()
                        .navigate(R.id.action_chatMessageFragment_to_chatUserProfileInfo, bundle)
                }
            }
        }
        var constraintsMute = mView.findViewById<ConstraintLayout>(R.id.constraintsMute)
        constraintsMute.setOnClickListener {
            popUp.dismiss()
            showDeleteGroupDialog()
        }
        var constraintsDeleteChat = mView.findViewById<ConstraintLayout>(R.id.constraintsDeleteChat)
        constraintsDeleteChat.setOnClickListener {
            popUp.dismiss()
            if (checkDeviceType()) {

            } else {
                if (view.findNavController().currentDestination?.id == R.id.chatMessageFragment) {
                    // view.findNavController().navigate(R.id.action_menuFragment_to_chatMessageFragment)
                }
            }
        }
    }

    //When click on the three dots
    private fun showChatGroupMenuMethod(view: View) {
        val mView: View = LayoutInflater.from(MainActivity.activity!!.get())
            .inflate(com.yapi.R.layout.group_chat_menu_options, null, false)
        var newWidth = screenWidth!! / 1.5
        val popUp =
            PopupWindow(mView, newWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT, false)
        popUp.isTouchable = true
        popUp.isFocusable = true
        popUp.isOutsideTouchable = true
        // val btnViewProfile = popUp.showAsDropDown(view.findViewById(com.yapi.R.id.ivChat_more_icon))

        var constraintsProfileChat =
            mView.findViewById<ConstraintLayout>(R.id.constraintsProfileChat)
        constraintsProfileChat.setOnClickListener {
            popUp.dismiss()

            if (checkDeviceType()) {
                EventBus.getDefault().post(MyMessageEvent(10, Constants.GROUP_PROFILE)) //post event
            } else {
                if (view.findNavController().currentDestination?.id == R.id.chatMessageFragment) {
                    var bundle = Bundle()
                    bundle.putString("userType", userType.toString())
                    view.findNavController()
                        .navigate(R.id.action_chatMessageFragment_to_chatGroupProfileInfo, bundle)
                }
            }


        }
        var constraintsMute = mView.findViewById<ConstraintLayout>(R.id.constraintsMute)
        constraintsMute.setOnClickListener {
            popUp.dismiss()
        }
        var constraintsLeaveGroup = mView.findViewById<ConstraintLayout>(R.id.constraintsLeaveGroup)
        constraintsLeaveGroup.setOnClickListener {
            popUp.dismiss()
            showLeaveGroupDialog()
        }

        var constraintsDeleteGroup =
            mView.findViewById<ConstraintLayout>(R.id.constraintsDeleteGroup)
        constraintsDeleteGroup.setOnClickListener {
            popUp.dismiss()
            showDeleteGroupDialog()
        }
    }

    private fun showLeaveGroupDialog() {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.leave_module_popup)

        dialog.show()
        var cardviewDeleteProfile = dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        var btnCancel = dialog.findViewById<AppCompatButton>(R.id.btnCancel)
        var ivCross = dialog.findViewById<ImageView>(R.id.ivCross)
        cardviewDeleteProfile.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        ivCross.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showDeleteGroupDialog() {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.delete_group_popup)

        dialog.show()
        var cardviewDeleteProfile = dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        var btnCancel = dialog.findViewById<AppCompatButton>(R.id.btnCancel)
        var ivCross = dialog.findViewById<ImageView>(R.id.ivCross)
        cardviewDeleteProfile.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        ivCross.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showViewAllMethod() {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.view_all_documents_layout)
        dialog.show()
        var cardviewViewAll = dialog.findViewById<CardView>(R.id.cardviewViewAll)
        cardviewViewAll.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()
        cardviewViewAll.layoutParams.height = (screenHeight!!.toDouble() / 1.5).toInt()
        var rvAllMedia = dialog.findViewById<RecyclerView>(R.id.rvAllMedia)
        var linearMedia = dialog.findViewById<LinearLayout>(R.id.linearMedia)
        var linearLinks = dialog.findViewById<LinearLayout>(R.id.linearLinks)
        var linearFiles = dialog.findViewById<LinearLayout>(R.id.linearFiles)

        var tvMediaText = dialog.findViewById<TextView>(R.id.tvMediaText)
        var viewMediaLine = dialog.findViewById<View>(R.id.viewMediaLine)

        var tvLinkText = dialog.findViewById<TextView>(R.id.tvLinkText)
        var viewLinkLine = dialog.findViewById<View>(R.id.viewLinkLine)

        var tvFilesText = dialog.findViewById<TextView>(R.id.tvFilesText)
        var viewFilesLine = dialog.findViewById<View>(R.id.viewFilesLine)
        var ivViewAllCross = dialog.findViewById<ImageView>(R.id.ivViewAllCross)
        var ivViewAllCrossOutside = dialog.findViewById<ImageView>(R.id.ivViewAllCrossOutside)

        if (checkDeviceType()) {
            ivViewAllCrossOutside.visibility = View.VISIBLE
            ivViewAllCross.visibility = View.GONE
        } else {
            ivViewAllCrossOutside.visibility = View.GONE
            ivViewAllCross.visibility = View.VISIBLE
        }

        setSelectedTab(tvMediaText, viewMediaLine)
        setDeSelectedTab(tvLinkText, viewLinkLine)
        setDeSelectedTab(tvFilesText, viewFilesLine)

        linearMedia.setOnClickListener {
            setSelectedTab(tvMediaText, viewMediaLine)
            setDeSelectedTab(tvLinkText, viewLinkLine)
            setDeSelectedTab(tvFilesText, viewFilesLine)
            setPhotoAdapterMethod(rvAllMedia)
        }

        linearLinks.setOnClickListener {
            setDeSelectedTab(tvMediaText, viewMediaLine)
            setSelectedTab(tvLinkText, viewLinkLine)
            setDeSelectedTab(tvFilesText, viewFilesLine)
            setLinkAdapterMethod(rvAllMedia)
        }

        linearFiles.setOnClickListener {
            setDeSelectedTab(tvMediaText, viewMediaLine)
            setDeSelectedTab(tvLinkText, viewLinkLine)
            setSelectedTab(tvFilesText, viewFilesLine)
            setFilesAdapterMethod(rvAllMedia)
        }

        ivViewAllCross.setOnClickListener {
            dialog.dismiss()
        }

        ivViewAllCrossOutside.setOnClickListener {
            dialog.dismiss()
        }

        setPhotoAdapterMethod(rvAllMedia)
    }

    private fun setDeSelectedTab(textView: TextView, view: View) {
        textView.setTextColor(MainActivity.activity!!.get()!!.getColor(R.color.darkGrey))
        view.setBackgroundColor(MainActivity.activity!!.get()!!.getColor(R.color.liteGrey))
    }

    private fun setSelectedTab(textView: TextView, view: View) {
        textView.setTextColor(MainActivity.activity!!.get()!!.getColor(R.color.blueColor))
        view.setBackgroundColor(MainActivity.activity!!.get()!!.getColor(R.color.blueColor))
    }

    private fun setPhotoAdapterMethod(rvAllMedia: RecyclerView) {
        var finalPerPhoto = screenWidth!!.toFloat() / 3.8f
        var mediaAdapter =
            RVPhotoMediaAdapter(MainActivity.activity!!.get()!!, finalPerPhoto.toInt())
        rvAllMedia.layoutManager = GridLayoutManager(MainActivity.activity!!.get()!!, 3)
        rvAllMedia.adapter = mediaAdapter
    }

    private fun setLinkAdapterMethod(rvAllMedia: RecyclerView) {
        var rvLinkAdapter = RVLinksAdapter(MainActivity.activity!!.get()!!)
        rvAllMedia.layoutManager = LinearLayoutManager(MainActivity.activity!!.get()!!)
        rvAllMedia.adapter = rvLinkAdapter
    }

    private fun setFilesAdapterMethod(rvAllMedia: RecyclerView) {
        var rvFilesAdapter = RVFilesAdapter(MainActivity.activity!!.get()!!)
        rvAllMedia.layoutManager = LinearLayoutManager(MainActivity.activity!!.get()!!)
        rvAllMedia.adapter = rvFilesAdapter
    }
}