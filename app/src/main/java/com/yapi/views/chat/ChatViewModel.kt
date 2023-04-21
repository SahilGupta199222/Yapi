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

    var chatValue = ObservableBoolean(true)
    var emailValue = ObservableBoolean(false)
    var smsValue = ObservableBoolean(false)

    var screenWidth: Int? = 0
    var screenHeight: Int? = 0

    var SECOND_FRAME_WIDTH: Int? = 0
  //  var screenHeight: Int? = 0
    var userType: String? = ""
    var sendDataValue = ObservableField("")
<<<<<<< HEAD

    var titleName = ObservableField("")
    var memberValue = ObservableField("")
    var emptyGroupMessageTitle = ObservableField("")

=======
>>>>>>> origin/master
    var backButtonVisible = ObservableBoolean(false)
    var groupImageVisible = ObservableBoolean(false)
    var groupIconVisible = ObservableBoolean(false)
    var liveUserVisible = ObservableBoolean(false)
    var noImageOnlyNameVisible = ObservableBoolean(false)
    var groupAllPhotos = ObservableBoolean(false)
    var showStyleStatus = ObservableBoolean(false)

    var leftAlignCStatus = ObservableBoolean(true)
    var centerAlignCStatus = ObservableBoolean(false)
    var rightAlignCStatus = ObservableBoolean(false)

<<<<<<< HEAD
=======

>>>>>>> origin/master
    fun onClick(view: View) {
        when (view.id) {

            R.id.imgTxtStyleChangeIconChatDemo -> {
                showStyleStatus.set(!showStyleStatus.get())
            }
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
//                showAddTemplateDialog()
                addLinkDialog()
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

    fun setAlignText(tabValue: Int) {
        leftAlignCStatus.set(false)
        centerAlignCStatus.set(false)
        rightAlignCStatus.set(false)
        if (tabValue == 1) {
            leftAlignCStatus.set(true)
        } else if (tabValue == 2) {
            centerAlignCStatus.set(true)
        } else {
            rightAlignCStatus.set(true)
        }
    }

    var keyboardHideData = MutableLiveData<Boolean>()

    //For show Template Dialog
    private fun showAddTemplateDialog() {
        val dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.add_template_layout)

        dialog.show()
        val cardviewAddTemplate = dialog.findViewById<CardView>(R.id.cardviewAddTemplate)
        val ivOutsideCloseGroup = dialog.findViewById<ImageView>(R.id.ivOutsideCloseGroup)
        val constraintsTopTemplate =
            dialog.findViewById<ConstraintLayout>(R.id.constraintsTopTemplate)
        val ivTemplateLogo = dialog.findViewById<ImageView>(R.id.ivTemplateLogo)
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

        val btnCancelTemplate = dialog.findViewById<AppCompatButton>(R.id.btnCancelTemplate)
        btnCancelTemplate.setOnClickListener {
            dialog.dismiss()
        }
        ivOutsideCloseGroup.setOnClickListener {
            dialog.dismiss()
        }
    }

    //For attachment dialog
    private fun attachmentPopupDialog() {
        val bottomSheetDialog =
            BottomSheetDialog(MainActivity.activity!!.get()!!, R.style.CustomBottomSheetDialogTheme)
        //bottomSheetDialog.window.setBackground(new ColorDrawable(Color.TRANSPARENT));
        val chatAttachBinding =
            ChatAttachementLayoutBinding.inflate(LayoutInflater.from(MainActivity.activity!!.get()!!))
        bottomSheetDialog.setContentView(chatAttachBinding.root)
        bottomSheetDialog.show()
    }

    //For Add Link Dialog
    private fun addLinkDialog() {
        val dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.add_chat_link_layout)

        dialog.show()
        val cardviewAddLink = dialog.findViewById<CardView>(R.id.cardviewAddLink)
        cardviewAddLink.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        val btnCancelAddLink = dialog.findViewById<AppCompatButton>(R.id.btnCancelAddLink)
        btnCancelAddLink.setOnClickListener {
            dialog.dismiss()
        }
    }

    //When click on the three dots
    private fun showChatMenuMethod(view: View) {
        val mView: View = LayoutInflater.from(MainActivity.activity!!.get())
            .inflate(R.layout.chat_menu_options, null, false)
        var newWidth=0.0
        if(checkDeviceType()){
            newWidth = SECOND_FRAME_WIDTH!! / 1.5
        }else
        {
             newWidth = screenWidth!! / 1.5
        }


        //   val popUp = PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false)
        val popUp =
            PopupWindow(mView, newWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT, false)
        // popUp.showAtLocation(mView, Gravity.RIGHT,0,0);
        popUp.isTouchable = true
        popUp.isFocusable = true
        popUp.isOutsideTouchable = true
        val btnViewProfile =

            popUp.showAsDropDown(view.findViewById(com.yapi.R.id.ivChat_more_icon))

        val constraintsProfileChat =
            mView.findViewById<ConstraintLayout>(R.id.constraintsProfileChat)
        constraintsProfileChat.setOnClickListener {
            popUp.dismiss()
            if (checkDeviceType()) {
                EventBus.getDefault().post(MyMessageEvent(10, Constants.USER_PROFILE)) //post event
            } else {
                if (view.findNavController().currentDestination?.id == R.id.chatMessageFragment) {
                    val bundle = Bundle()
                    bundle.putString("userType", userType.toString())
                    view.findNavController()
                        .navigate(R.id.action_chatMessageFragment_to_chatUserProfileInfo, bundle)
                }
            }
        }
        val constraintsMute = mView.findViewById<ConstraintLayout>(R.id.constraintsMute)
        constraintsMute.setOnClickListener {
            popUp.dismiss()
//            showDeleteGroupDialog()
        }
        val constraintsDeleteChat = mView.findViewById<ConstraintLayout>(R.id.constraintsDeleteChat)
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

        if(checkDeviceType())
        {
           newWidth= SECOND_FRAME_WIDTH!!.toDouble()/ 3
        }
        val popUp =
            PopupWindow(mView, newWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT, false)
        popUp.isTouchable = true
        popUp.isFocusable = true
        popUp.isOutsideTouchable = true
        popUp.showAsDropDown(view.findViewById(R.id.ivChat_more_icon))

        // val btnViewProfile = popUp.showAsDropDown(view.findViewById(com.yapi.R.id.ivChat_more_icon))

        val constraintsProfileChat =
            mView.findViewById<ConstraintLayout>(R.id.constraintsProfileChat)
        constraintsProfileChat.setOnClickListener {
            popUp.dismiss()

            if (checkDeviceType()) {
                EventBus.getDefault().post(MyMessageEvent(10, Constants.GROUP_PROFILE)) //post event
            } else {
                if (view.findNavController().currentDestination?.id == R.id.chatMessageFragment) {
                    val bundle = Bundle()
                    bundle.putString("userType", userType.toString())
                    view.findNavController()
                        .navigate(R.id.action_chatMessageFragment_to_chatGroupProfileInfo, bundle)
                }
            }


        }
        val constraintsMute = mView.findViewById<ConstraintLayout>(R.id.constraintsMute)
        constraintsMute.setOnClickListener {
            popUp.dismiss()
        }
        val constraintsLeaveGroup = mView.findViewById<ConstraintLayout>(R.id.constraintsLeaveGroup)
        constraintsLeaveGroup.setOnClickListener {
            popUp.dismiss()
            showLeaveGroupDialog()
        }

        val constraintsDeleteGroup =
            mView.findViewById<ConstraintLayout>(R.id.constraintsDeleteGroup)
        constraintsDeleteGroup.setOnClickListener {
            popUp.dismiss()
            showDeleteGroupDialog()
        }
    }

    private fun showLeaveGroupDialog() {
        val dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.leave_module_popup)

        dialog.show()
        val cardviewDeleteProfile = dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        val btnCancel = dialog.findViewById<AppCompatButton>(R.id.btnCancel)
        val ivCross = dialog.findViewById<ImageView>(R.id.ivCross)
        cardviewDeleteProfile.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        ivCross.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showDeleteGroupDialog() {
        val dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.delete_group_popup)

        dialog.show()
        val cardviewDeleteProfile = dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        val btnCancel = dialog.findViewById<AppCompatButton>(R.id.btnCancel)
        val ivCross = dialog.findViewById<ImageView>(R.id.ivCross)
        cardviewDeleteProfile.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        ivCross.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showViewAllMethod() {
        val dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.view_all_documents_layout)
        dialog.show()
        val cardviewViewAll = dialog.findViewById<CardView>(R.id.cardviewViewAll)
        cardviewViewAll.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()
        cardviewViewAll.layoutParams.height = (screenHeight!!.toDouble() / 1.5).toInt()
        val rvAllMedia = dialog.findViewById<RecyclerView>(R.id.rvAllMedia)
        val linearMedia = dialog.findViewById<LinearLayout>(R.id.linearMedia)
        val linearLinks = dialog.findViewById<LinearLayout>(R.id.linearLinks)
        val linearFiles = dialog.findViewById<LinearLayout>(R.id.linearFiles)

        val tvMediaText = dialog.findViewById<TextView>(R.id.tvMediaText)
        val viewMediaLine = dialog.findViewById<View>(R.id.viewMediaLine)

        val tvLinkText = dialog.findViewById<TextView>(R.id.tvLinkText)
        val viewLinkLine = dialog.findViewById<View>(R.id.viewLinkLine)

        val tvFilesText = dialog.findViewById<TextView>(R.id.tvFilesText)
        val viewFilesLine = dialog.findViewById<View>(R.id.viewFilesLine)
        val ivViewAllCross = dialog.findViewById<ImageView>(R.id.ivViewAllCross)
        val ivViewAllCrossOutside = dialog.findViewById<ImageView>(R.id.ivViewAllCrossOutside)

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
        val finalPerPhoto = screenWidth!!.toFloat() / 3.8f
        val mediaAdapter =
            RVPhotoMediaAdapter(MainActivity.activity!!.get()!!, finalPerPhoto.toInt())
        rvAllMedia.layoutManager = GridLayoutManager(MainActivity.activity!!.get()!!, 3)
        rvAllMedia.adapter = mediaAdapter
    }

    private fun setLinkAdapterMethod(rvAllMedia: RecyclerView) {
        val rvLinkAdapter = RVLinksAdapter(MainActivity.activity!!.get()!!)
        rvAllMedia.layoutManager = LinearLayoutManager(MainActivity.activity!!.get()!!)
        rvAllMedia.adapter = rvLinkAdapter
    }

    private fun setFilesAdapterMethod(rvAllMedia: RecyclerView) {
        val rvFilesAdapter = RVFilesAdapter(MainActivity.activity!!.get()!!)
        rvAllMedia.layoutManager = LinearLayoutManager(MainActivity.activity!!.get()!!)
        rvAllMedia.adapter = rvFilesAdapter
    }
}