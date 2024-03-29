package com.yapi.views.chat.chatGroupInfo

import android.app.Dialog
import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.*
import com.yapi.pref.PreferenceFile
import com.yapi.recycleradapter.RecyclerAdapter
import com.yapi.views.chat.chatUserInfo.TempModel
import com.yapi.views.profile.ProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import org.greenrobot.eventbus.EventBus
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ChatGroupInfoViewModel @Inject constructor(val repository:Repository,
                                                 val preferenceFile: PreferenceFile,
@Named("token") val userToken:String):ViewModel() {
    var screenWidth: Int? = 0
    var screenHeight: Int? = 0
    init {

    }

    val rvFilesAdapter by lazy { RecyclerAdapter<TempModel>(R.layout.rv_file_adapter_layout) }
    val rvTemplatesAdapter by lazy { RecyclerAdapter<TempModel>(R.layout.rvtemplate_layout) }

    var userType= ObservableField("")
    var userInformation=ObservableBoolean(false)
var groupPhoto=ObservableField("")
var groupName=ObservableField("")
var groupNameTag=ObservableField("")
var groupPhotoVisibility=ObservableBoolean(false)
var screenDataVisibility=ObservableBoolean(false)

    fun onClick(view:View)
    {
        when(view.id)
        {
            R.id.ivBackGroupInfo->{
                view.findNavController().navigateUp()
            }
            R.id.ivmoreGroupInfo->{
                showChatEditGroupMenuMethod(view)
            }
        }
    }

    fun setAdapterData()
    {
        var fileList=ArrayList<TempModel>()
        fileList.add(TempModel("AAA"))
        fileList.add(TempModel("AAA"))
        fileList.add(TempModel("AAA"))
        rvFilesAdapter.addItems(fileList)
        rvFilesAdapter.notifyDataSetChanged()
    }

    fun setTemplateAdapterData()
    {
        var fileList=ArrayList<TempModel>()
        fileList.add(TempModel("AAA"))
        fileList.add(TempModel("AAA"))
        fileList.add(TempModel("AAA"))
        rvTemplatesAdapter.addItems(fileList)
        rvTemplatesAdapter.notifyDataSetChanged()
    }
    private fun showChatEditGroupMenuMethod(view: View) {

        val mView: View = LayoutInflater.from(MainActivity.activity!!.get())
            .inflate(com.yapi.R.layout.group_chat_menu_options, null, false)
        var newWidth = screenWidth!! / 1.5
        val popUp =
            PopupWindow(mView, newWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT, false)
        popUp.isTouchable = true
        popUp.isFocusable = true
        popUp.isOutsideTouchable = true
        popUp.showAsDropDown(view.findViewById(R.id.ivmoreGroupInfo))

        // val btnViewProfile = popUp.showAsDropDown(view.findViewById(com.yapi.R.id.ivChat_more_icon))

        var firstTitle =
            mView.findViewById<TextView>(R.id.tvProfile)
        firstTitle.text="Edit Group Info"

        var constraintsProfileChat =
            mView.findViewById<ConstraintLayout>(R.id.constraintsProfileChat)
        constraintsProfileChat.setOnClickListener {
            popUp.dismiss()

            if (checkDeviceType()) {
                EventBus.getDefault().post(MyMessageEvent(10, Constants.GROUP_PROFILE)) //post event
            } else {
                    view.findNavController()
                        .navigate(R.id.action_chatGroupProfileInfo_to_groupInfoFragment)
            }
        }
        var constraintsMute = mView.findViewById<ConstraintLayout>(R.id.constraintsMute)
        constraintsMute.setOnClickListener {
            popUp.dismiss()
        }
        var constraintsLeaveGroup = mView.findViewById<ConstraintLayout>(R.id.constraintsLeaveGroup)
        constraintsLeaveGroup.setOnClickListener {
            popUp.dismiss()
            showLeaveGroupDialog(view)
        }

        var constraintsDeleteGroup =
            mView.findViewById<ConstraintLayout>(R.id.constraintsDeleteGroup)
        constraintsDeleteGroup.setOnClickListener {
            popUp.dismiss()
            showDeleteGroupDialog(view)
        }
    }

    fun fetchGroupOrTeamData(teamId:String) {
        Log.e("Token111====", preferenceFile.fetchStringValue(Constants.USER_TOKEN))
        repository.makeCall(true,
            requestProcessor = object : ApiProcessor<Response<ChatGroupInfoResponse>> {
                override fun onSuccess(success: Response<ChatGroupInfoResponse>) {
                    Log.e("Resposne_Dataaaa===", success.body().toString())
                    groupName.set(success.body()!!.data.name)
                    groupNameTag.set(convertFromFullNameToTwoString(success.body()!!.data.name))
                    groupPhoto.set(success.body()!!.data.image_url)

                    if(success.body()!!.data.image_url.isNotEmpty())
                    {
                        groupPhotoVisibility.set(true)
                    }else
                    {
                        groupPhotoVisibility.set(false)
                    }
                    screenDataVisibility.set(true)
                  /*  profileData = success.body()!!.data

                    //showTopNameTag.set(success.body()!!.data.name!!.substring(0, 1).uppercase(Locale.getDefault()))
                    showTopNameTag.set(convertFromFullNameToTwoString(success.body()!!.data.name!!))

                    if (success.body()!!.data.profile_pic_url != "") {
                        noImageOnlyNameVisible.set(false)
                        photoUrl.set(success.body()!!.data.profile_pic_url)
                    } else {
                        noImageOnlyNameVisible.set(true)
                    }

                    aboutValue.set(success.body()!!.data.about.toString().trim())
                    if (success.body()!!.data.about!!.trim().isEmpty()) {
                        aboutVisiblityValue.set(false)
                    } else {
                        aboutVisiblityValue.set(true)
                    }
*/
                    //phoneCountryValue.set(success.body()!!.data.country_code.toString())
                    /*   var bundle= Bundle()
                       bundle.putString("email",emailFieldValue.get())
                       view.findNavController().navigate(R.id.action_signInFragment_to_signUpCodeFragment,bundle)*/
                }

                override fun onError(message: String) {
                    MainActivity.activity!!.get()!!.showMessage(message)
                }

                override suspend fun sendRequest(retrofitApi: RetrofitAPI): Response<ChatGroupInfoResponse> {
                    Log.e("mflfldddff==", preferenceFile.fetchStringValue(Constants.LOGIN_USER_ID))
                    return retrofitApi.fetchTeamOrGroupDetailAPI(userToken, teamId)
                }
            })
    }

    private fun showLeaveGroupDialog(screenView:View) {
        val dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.leave_module_popup)

        dialog.show()
        val cardviewDeleteProfile = dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        val btnCancel = dialog.findViewById<AppCompatButton>(R.id.btnCancel)
        val btnDelGroup = dialog.findViewById<AppCompatButton>(R.id.btnDelGroup)
        val ivCross = dialog.findViewById<ImageView>(R.id.ivCross)
        val ivCrossOutsideLeaveGroup = dialog.findViewById<ImageView>(R.id.ivCrossOutsideLeaveGroup)
        val leaveGroupConstraints = dialog.findViewById<ConstraintLayout>(R.id.leaveGroupConstraints)
        // cardviewDeleteProfile.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        var newWidth=0
        var newHeight=0
        if(checkDeviceType()){
            newWidth =  ConstraintLayout.LayoutParams.WRAP_CONTENT
            newHeight =  ConstraintLayout.LayoutParams.WRAP_CONTENT

            Log.e("efmefkmefefef===",newWidth.toString())
            Log.e("efmefkmefefef111===",newHeight.toString())
            cardviewDeleteProfile.layoutParams.width = newWidth.toInt()
            cardviewDeleteProfile.layoutParams.height = newHeight.toInt()
        }else {
            //    newWidth =  ConstraintLayout.LayoutParams.MATCH_PARENT
            newWidth = (screenWidth!!.toDouble() / 1).toInt()
            newHeight = ConstraintLayout.LayoutParams.WRAP_CONTENT
            leaveGroupConstraints.layoutParams.width = newWidth.toInt()
            leaveGroupConstraints.layoutParams.height = newHeight.toInt()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        ivCross.setOnClickListener {
            dialog.dismiss()
        }
        ivCrossOutsideLeaveGroup.setOnClickListener {
            dialog.dismiss()
        }

        if(checkDeviceType())
        {
            ivCrossOutsideLeaveGroup.visibility=View.VISIBLE
            ivCross.visibility=View.GONE
        }else
        {
            ivCrossOutsideLeaveGroup.visibility=View.GONE
            ivCross.visibility=View.VISIBLE
        }

        btnDelGroup.setOnClickListener {
            dialog.dismiss()
            if(Constants.API_CALL_DEMO){
                //leaveGroupMethod(groupId.get().toString(),screenView)
            }}
    }

    private fun showDeleteGroupDialog(screenView:View) {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.delete_group_popup)

        dialog.show()
        val cardviewDeleteProfile = dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        val deleteGroupConstraints = dialog.findViewById<ConstraintLayout>(R.id.deleteGroupConstraints)
        val btnCancel = dialog.findViewById<AppCompatButton>(R.id.btnCancel)
        val btnDelGroup = dialog.findViewById<AppCompatButton>(R.id.btnDelGroup)
        val ivCross = dialog.findViewById<ImageView>(R.id.ivCross)
        val ivCrossOutsideDeleteGroup = dialog.findViewById<ImageView>(R.id.ivCrossOutsideDeleteGroup)
        cardviewDeleteProfile.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        var newWidth=0
        var newHeight=0
        if(checkDeviceType()){
            newWidth =  ConstraintLayout.LayoutParams.WRAP_CONTENT
            newHeight =  ConstraintLayout.LayoutParams.WRAP_CONTENT

            Log.e("efmefkmefefef===",newWidth.toString())
            Log.e("efmefkmefefef111===",newHeight.toString())
            cardviewDeleteProfile.layoutParams.width = newWidth.toInt()
            cardviewDeleteProfile.layoutParams.height = newHeight.toInt()
        }else {
            //    newWidth =  ConstraintLayout.LayoutParams.MATCH_PARENT
            newWidth = (screenWidth!!.toDouble() / 1).toInt()
            newHeight = ConstraintLayout.LayoutParams.WRAP_CONTENT
            deleteGroupConstraints.layoutParams.width = newWidth.toInt()
            deleteGroupConstraints.layoutParams.height = newHeight.toInt()
        }

        if(checkDeviceType())
        {
            ivCrossOutsideDeleteGroup.visibility=View.VISIBLE
            ivCross.visibility=View.GONE
        }else
        {
            ivCrossOutsideDeleteGroup.visibility=View.GONE
            ivCross.visibility=View.VISIBLE
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        ivCrossOutsideDeleteGroup.setOnClickListener {
            dialog.dismiss()
        }

        btnDelGroup.setOnClickListener {
            dialog.dismiss()
            if(Constants.API_CALL_DEMO) {
              //  deleteGroupMethod(groupId.get().toString(), screenView)
            }
        }

        ivCross.setOnClickListener {
            dialog.dismiss()
        }
    }
}