package com.yapi.views.menu_screen

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.android.material.chip.Chip
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.*
import com.yapi.databinding.CreateNewConversationPopupBinding
import com.yapi.pref.PreferenceFile
import com.yapi.views.sign_in.SignInErrorData
import com.yapi.views.signup_code.LoginUserData
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MenuViewModel @Inject constructor(val preferenceFile: PreferenceFile,
val repository: Repository,@Named("token") val userToken:String) : ViewModel() {

    var groupData=MutableLiveData<AllData>()
    var screenWidth: Int? = 0
    var openProfileScreenData = MutableLiveData<Boolean>()
    var openSearchScreenData = MutableLiveData<Boolean>()

    var noImageOnlyNameVisible=ObservableBoolean(false)

  //  var noImageOnlyNameVisible = MutableLiveData<Boolean>()
    var showTopNameTag=ObservableField("")

    var userPhotoUrl=ObservableField("")

    var loginUserData: LoginUserData?=null
    fun onClick(view: View) {
        when (view.id) {
            R.id.layoutSearch,R.id.etSearchMenu->{
                if(checkDeviceType()){
                    openSearchScreenData.value=true
                    //SearchFragment.newInstanceSearch("").showNow(view.re, " SimpleDialog.TAG")
                }else
                {
                if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
                    view.findNavController()
                        .navigate(R.id.action_menuFragment_to_searchFragment)
                }
            }
            }

          R.id.imgProfilePicCustomerList,R.id.relNameValue -> {
                val mView: View = LayoutInflater.from(MainActivity.activity!!.get())
                    .inflate(com.yapi.R.layout.menu_popup_options, null, false)
                var newWidth=0.0

                if(checkDeviceType()){
                    newWidth =  LinearLayout.LayoutParams.WRAP_CONTENT.toDouble()
                }else
                {
                     newWidth = screenWidth!! / 1.5
                }

                //   val popUp = PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false)
                val popUp = PopupWindow(mView,
                    newWidth.toInt(),
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    false)
                // popUp.showAtLocation(mView, Gravity.RIGHT,0,0);
                popUp.isTouchable = true
                popUp.isFocusable = true
                popUp.isOutsideTouchable = true

                if(view.id==R.id.imgProfilePicCustomerList){
                    popUp.showAsDropDown(view.findViewById(com.yapi.R.id.imgProfilePicCustomerList))
                }else
                {
                    popUp.showAsDropDown(view.findViewById(com.yapi.R.id.relNameValue))
                }

                var constraintsProfile =
                    mView.findViewById<ConstraintLayout>(R.id.constraintsProfile)
                constraintsProfile.setOnClickListener {
                    popUp.dismiss()
                    if (checkDeviceType()) {
                        openProfileScreenData.value = true
                    } else {
                        if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
                            view.findNavController()
                                .navigate(R.id.action_menuFragment_to_profileFragment)
                        }
                    }
                }
                var constraintsSettings =
                    mView.findViewById<ConstraintLayout>(R.id.constraintsSettings)
                constraintsSettings.setOnClickListener {
                    popUp.dismiss()
                    //  showLeaveGroupDialog()
                    // showDeleteGroupDialog()
                    /*   if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
                           view.findNavController()
                               .navigate(R.id.action_menuFragment_to_profileFragment)
                       }*/
                }
                var constraintsLogout = mView.findViewById<ConstraintLayout>(R.id.constraintsLogout)
                constraintsLogout.setOnClickListener {
                    popUp.dismiss()
                    showLogoutDialog(view)
                }


//                       if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
//                           view.findNavController().navigate(R.id.leaveGroupFragment)
//                       }
//                   val dialog=Dialog(MainActivity.activity!!.get()!!)

//                   dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
//                   dialog.setContentView(R.layout.delete_profile_popup)

//                   dialog.show()
//                   var cardviewDeleteProfile=dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
//                   cardviewDeleteProfile.layoutParams.width=(screenWidth!!.toDouble()/1.1).toInt()

            }
            /*R.id.layoutSearch,R.id.etSearchMenu->{
                if(checkDeviceType()){

                }else
                {
                if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
                    view.findNavController()
                        .navigate(R.id.action_menuFragment_to_searchFragment)
                }
            }}*/

            /*  R.id.layoutAddNewGroupsMenu-> {
                 *//* if (view.findNavController().currentDestination?.id == R.id.menuFragment) {

                 view.findNavController()
                     .navigate(R.id.action_menuFragment_to_createGroupFragment)
             }*//*
         }*/
        }
    }

//For show Logout Popup
    private fun showLogoutDialog(view:View) {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.logout_popup_layout)
        dialog.show()

        var cardviewLogoutProfile = dialog.findViewById<CardView>(R.id.cardviewLogoutProfile)
        var ivCrossOutsideLogout = dialog.findViewById<ImageView>(R.id.ivCrossOutsideLogout)
        var logoutConstraints = dialog.findViewById<ConstraintLayout>(R.id.logoutConstraints)
        var newWidth=0
        var newHeight=0
        if(checkDeviceType()){
            newWidth =  ConstraintLayout.LayoutParams.WRAP_CONTENT
            newHeight =  ConstraintLayout.LayoutParams.WRAP_CONTENT

            Log.e("efmefkmefefef===",newWidth.toString())
            Log.e("efmefkmefefef111===",newHeight.toString())
            cardviewLogoutProfile.layoutParams.width = newWidth.toInt()
            cardviewLogoutProfile.layoutParams.height = newHeight.toInt()
        }else {
        //    newWidth =  ConstraintLayout.LayoutParams.MATCH_PARENT
           newWidth = (screenWidth!!.toDouble() / 1).toInt()
            newHeight = ConstraintLayout.LayoutParams.WRAP_CONTENT
            logoutConstraints.layoutParams.width = newWidth.toInt()
            logoutConstraints.layoutParams.height = newHeight.toInt()
        }

        var btnCancel=dialog.findViewById<AppCompatButton>(R.id.btnCancel)
        var btnLogout=dialog.findViewById<AppCompatButton>(R.id.btnLogout)
        var ivCrossLogout=dialog.findViewById<ImageView>(R.id.ivCrossLogout)

    if(checkDeviceType())
    {
        ivCrossLogout.visibility=View.GONE
        ivCrossOutsideLogout.visibility=View.VISIBLE
    }else
    {
        ivCrossLogout.visibility=View.VISIBLE
        ivCrossOutsideLogout.visibility=View.GONE
    }

        btnLogout.setOnClickListener {
            dialog.dismiss()
            preferenceFile.clearAllPref()
            if(checkDeviceType()){
                var intent=Intent(MainActivity.activity!!.get(),MainActivity::class.java)
                MainActivity.activity!!.get()!!.startActivity(intent)
            }else
            {
                view.findNavController()
                    .navigate(R.id.action_menuFragment_to_signInFragment)
            }
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        ivCrossLogout.setOnClickListener {
            dialog.dismiss()
        }
    ivCrossOutsideLogout.setOnClickListener {
            dialog.dismiss()
        }
    }


    fun showLeaveGroupDialog() {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.leave_module_popup)

        dialog.show()
        var cardviewDeleteProfile = dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        cardviewDeleteProfile.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()
    }

    fun showDeleteGroupDialog() {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.delete_group_popup)

        dialog.show()
        var cardviewDeleteProfile = dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        cardviewDeleteProfile.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()
    }


    fun fetchGroupDataMethod():LiveData<AllData> {
        Log.e("Token111====", preferenceFile.fetchStringValue(Constants.USER_TOKEN))
        repository.makeCall(true,
            requestProcessor = object : ApiProcessor<Response<GroupMenuResponse>> {
                override fun onSuccess(success: Response<GroupMenuResponse>) {
                    Log.e("Resposne_Dataaaa===", success.body().toString())

                    groupData.value=success.body()!!.data

                }

                override fun onError(message: String) {
                    MainActivity.activity!!.get()!!.showMessage(message)
                }

                override suspend fun sendRequest(retrofitApi: RetrofitAPI): Response<GroupMenuResponse> {
                    return retrofitApi.fetchAllGroupData(userToken)
                }
            })
        return groupData
    }


    //For show Logout Popup
     fun showCreateConversationDialog() {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        var createConversationBinding=CreateNewConversationPopupBinding.inflate(LayoutInflater.from(MainActivity.activity!!.get()))
   //     dialog.setContentView(R.layout.create_new_conversation_popup)
        dialog.setContentView(createConversationBinding.root)
        dialog.show()

        createConversationBinding.apply {
        createConversationBinding.etChipAddPeopleEmail.doOnTextChanged { text, start, before, count ->
            if (text?.isNotEmpty() == true) {
                createConversationBinding.layoutAddPeopleAddPeopleEmail.visibility = View.VISIBLE
                createConversationBinding.txtAddPeopleAddPeopleEmail.text = text
                createConversationBinding.txtUserNameAddPeopleEmail.text = text[0].toString()
            } else {
                createConversationBinding.layoutAddPeopleAddPeopleEmail.visibility = View.GONE
            }
        }
        createConversationBinding.layoutAddPeopleAddPeopleEmail.setOnClickListener {
            if (etChipAddPeopleEmail.text?.isNotEmpty() == true) {
                val msg = MainActivity.activity!!.get()!!.isEmailValid(etChipAddPeopleEmail.text.toString())
                if (msg.isEmpty()) {
                    var alreadyExistEmail=false
                    if(chipGroupAddPeopleEmail?.childCount!!>0) {
                        for (i in 0 until chipGroupAddPeopleEmail?.childCount!!) {
                            val chipView = chipGroupAddPeopleEmail?.getChildAt(i) as Chip
                         /*   val title = chipView.text.toString()
                            if (title.equals(etChipAddPeopleEmail.text.toString())) {
                                alreadyExistEmail = true
                                break
                            }*/
                        }
                    }
                    txtErrorEmailAddPeople.setText("")
                    addChipToGroup(MainActivity.activity!!.get()!!,
                        etChipAddPeopleEmail.text.toString(),createConversationBinding)
                    layoutAddPeopleAddPeopleEmail.visibility =
                        View.GONE
                    etChipAddPeopleEmail.text?.clear()
                }else{
                   var data= SignInErrorData("Please enter correct email", 1)

                    emailErrorMethod(data,txtErrorEmailAddPeople,chipLayoutAddPeopleEmail,MainActivity.activity!!.get()!!)
                    //errorData.value = SignInErrorData("Please enter correct email", 1)
                }
            }
        }
        }

        var newWidth=0
        var newHeight=0

        if(checkDeviceType()){
            newWidth = (screenWidth!!.toDouble() / 2).toInt()
           // newWidth =  ConstraintLayout.LayoutParams.WRAP_CONTENT
            newHeight =  ConstraintLayout.LayoutParams.WRAP_CONTENT

            Log.e("efmefkmefefef===",newWidth.toString())
            Log.e("efmefkmefefef111===",newHeight.toString())
            createConversationBinding.cardviewConversations.layoutParams.width = newWidth.toInt()
            createConversationBinding.cardviewConversations.layoutParams.height = newHeight.toInt()
        }else {
            //    newWidth =  ConstraintLayout.LayoutParams.MATCH_PARENT
            newWidth = (screenWidth!!.toDouble() / 1).toInt()
            newHeight = ConstraintLayout.LayoutParams.WRAP_CONTENT
            createConversationBinding.CreateNewConversationsConstraints.layoutParams.width = newWidth.toInt()
            createConversationBinding.CreateNewConversationsConstraints.layoutParams.height = newHeight.toInt()
        }

  /*      if(checkDeviceType()){
            newWidth =  ConstraintLayout.LayoutParams.WRAP_CONTENT
            newHeight =  ConstraintLayout.LayoutParams.WRAP_CONTENT

            Log.e("efmefkmefefef===",newWidth.toString())
            Log.e("efmefkmefefef111===",newHeight.toString())
            createConversationBinding.cardviewLogoutProfile.layoutParams.width = newWidth.toInt()
            createConversationBinding.cardviewLogoutProfile.layoutParams.height = newHeight.toInt()
        }else {
            //    newWidth =  ConstraintLayout.LayoutParams.MATCH_PARENT
            newWidth = (screenWidth!!.toDouble() / 1).toInt()
            newHeight = ConstraintLayout.LayoutParams.WRAP_CONTENT
            logoutConstraints.layoutParams.width = newWidth.toInt()
            logoutConstraints.layoutParams.height = newHeight.toInt()
        }*/

       /* var btnCancel=dialog.findViewById<AppCompatButton>(R.id.btnCancel)
        var btnLogout=dialog.findViewById<AppCompatButton>(R.id.btnLogout)
        var ivCrossLogout=dialog.findViewById<ImageView>(R.id.ivCrossLogout)
*/
        if(checkDeviceType())
        {
          //  ivCrossLogout.visibility=View.GONE
            createConversationBinding.ivCrossOutsideConversation.visibility=View.VISIBLE
        }else
        {
            createConversationBinding.ivCrossOutsideConversation.visibility=View.GONE
        }

        createConversationBinding.btnCreateConversation.setOnClickListener {
            if(createConversationBinding.chipGroupAddPeopleEmail?.childCount==0)
            {
                var data= SignInErrorData("Please enter email", 1)
                emailErrorMethod(data,createConversationBinding.txtErrorEmailAddPeople,createConversationBinding.chipLayoutAddPeopleEmail,MainActivity.activity!!.get()!!)
            }else
            {
                dialog.dismiss()
            }
        }
        createConversationBinding.btnCancelConversation.setOnClickListener {
                dialog.dismiss()
        }
            createConversationBinding.ivCrossOutsideConversation.setOnClickListener {
                dialog.dismiss()
        }
    }

    fun addChipToGroup(context: Context, person: String,
                       createConversationBinding: CreateNewConversationPopupBinding) {
        val chip = Chip(context)
        chip.text = person
        chip.setTextColor(ContextCompat.getColor(context, R.color.blueColor))
        chip.chipBackgroundColor =
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.liteBlueForDrawable))
        chip.chipCornerRadius = context.resources.getDimension(com.intuit.sdp.R.dimen._5sdp)
        chip.isCloseIconVisible = true
        chip.closeIcon =
            ContextCompat.getDrawable(context, R.drawable.ic_cross_icon)
        chip.closeIconSize=context.resources.getDimension(com.intuit.sdp.R.dimen._8sdp)
        var paddingValue= context.resources.getDimension(com.intuit.sdp.R.dimen._5sdp).toInt()
        chip.isCheckable = false
        chip.closeIconTint =
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.darkLiteGrey))
        //.setTextSize(13f)
        chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected);
        chip.setPadding(paddingValue,paddingValue,paddingValue,paddingValue)

        createConversationBinding.chipGroupAddPeopleEmail.addView(chip as View)
        chip.setOnCloseIconClickListener {
            createConversationBinding.chipGroupAddPeopleEmail.removeView(chip as View)
        }
    }

    fun emailErrorMethod(
        data: SignInErrorData,
        txtErrorEmailAddPeople: AppCompatTextView,
        chipLayoutAddPeopleEmail: ConstraintLayout,
        activity: Activity
    )
    {
        if(data!=null && data.message!="")
        {
            txtErrorEmailAddPeople.setText(data.message)
            changeBackgroundTintForError(chipLayoutAddPeopleEmail!!,activity.resources.getColor(
                R.color.error_box_color),
                activity.resources.getColor(R.color.error_border_color),-1)
        }else
        {
            txtErrorEmailAddPeople.setText("")
            //   binding.chipLayoutAddPeopleEmail!!.setbackg
            changeBackgroundTintForError(chipLayoutAddPeopleEmail!!, activity.resources.getColor(
                R.color.white),
                activity.resources.getColor(R.color.liteGrey),activity.resources.getColor(R.color.information_profile_back_box))
        }
    }

 /*   fun addChipToGroup(
        context: Context,
        person: String,
        createConversationBinding: CreateNewConversationPopupBinding
    ) {
        val chip = ChipGroup(context)
       // chip.text = person
        var linearLayout=LinearLayout(MainActivity.activity!!.get())
        linearLayout.orientation=LinearLayout.HORIZONTAL
        var textView=TextView(MainActivity.activity!!.get())
       // chip.setChipIconResource(R.drawable.demo_photo)
        textView.setTextColor(ContextCompat.getColor(context, R.color.blueColor))
        linearLayout.setBackgroundColor(MainActivity.activity!!.get()!!.resources.getColor(R.color.liteBlueForDrawable))
       // chip.chipCornerRadius = context.resources.getDimension(com.intuit.sdp.R.dimen._5sdp)
       // chip.isCloseIconVisible = true
      //  chip.closeIcon = ContextCompat.getDrawable(context, R.drawable.ic_cross_icon)
        //chip.closeIconSize=context.resources.getDimension(com.intuit.sdp.R.dimen._8sdp)
        var paddingValue= context.resources.getDimension(com.intuit.sdp.R.dimen._5sdp).toInt()
      //  chip.isCheckable = false
        //chip.closeIconTint = ColorStateList.valueOf(ContextCompat.getColor(context, com.yapi.R.color.darkLiteGrey))
        //.setTextSize(13f)
      //  chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected);
        chip.setPadding(paddingValue,paddingValue,paddingValue,paddingValue)

        linearLayout.addView(textView)
        chip.addView(linearLayout)
        createConversationBinding.chipGroupAddPeopleEmail.addView(chip as View)
        *//*chip.setOnCloseIconClickListener {
            createConversationBinding.chipGroupAddPeopleEmail.removeView(chip as View)
        }*//*
    }*/
}