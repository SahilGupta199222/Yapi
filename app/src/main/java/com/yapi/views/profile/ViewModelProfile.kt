package com.yapi.views.profile

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.*
import com.yapi.pref.PreferenceFile
import com.yapi.views.edit_profile.EditProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelProfile @Inject constructor(val repository: Repository,val preferenceFile: PreferenceFile) : ViewModel() {
    private  var profileData: ProfileData?=null
    var openEditProfileData=MutableLiveData<Boolean>()
var dismissDialogData=MutableLiveData<Boolean>()

    var nameValue=ObservableField("")
    var userNameValue=ObservableField("")
    var aboutValue=ObservableField("")
    var emailValue=ObservableField("")
    var phoneValue=ObservableField("")
    var phoneCountryValue=ObservableField("")

    var topProfileVisibility=ObservableBoolean(false)

    var screenWidth:Int?=0
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnEditProfile -> {
                if(checkDeviceType()){
                    openEditProfileData.value=true
                }else
                {
                    if (view.findNavController().currentDestination?.id == R.id.profileFragment) {
                        var bundle= Bundle()
                        if(profileData!=null) {
                            bundle.putSerializable("profile_data", profileData)
                        }
                        view.findNavController()
                            .navigate(R.id.action_profileFragment_to_editProfileFragment,bundle)
                    }
                }
            }
            R.id.layoutDeleteAccountProfile->{
                deleteAccountDialog(view)
            }
            R.id.layoutDeActiveProfile->{
                deActiveAccountDialog(view)
            }
            R.id.imgCancelProfile,R.id.ivOutsideCloseProfile-> {
                if(checkDeviceType()){
                    dismissDialogData.value=true
                }else
                {
                    if (view.findNavController().currentDestination?.id == R.id.profileFragment) {
                        view.findNavController().popBackStack()
                    }
                }
            }
            R.id.layoutProfile,R.id.layoutScrollViewProfile->{
                //for hide keyboard
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    private fun deleteAccountDialog(view:View) {
        val dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.delete_profile_popup)
//        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        val cancelBtn=dialog.findViewById<AppCompatButton>(R.id.btnProfileCancel)
        val deleteBtn=dialog.findViewById<AppCompatButton>(R.id.btnProfileDelete)
        val ivCross=dialog.findViewById<AppCompatImageView>(R.id.ivCross)

        ivCross.setOnClickListener {
            dialog.dismiss()
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        deleteBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()

        var cardviewDeleteProfile=dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        cardviewDeleteProfile.layoutParams.width=(screenWidth!!.toDouble()/1.1).toInt()
    }
    private fun deActiveAccountDialog(view:View){
        val dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.dialog_de_activie_profile)
//        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        val cancelBtn=dialog.findViewById<AppCompatButton>(R.id.btnCancelDeActiveDialog)
        val deActivateBtn=dialog.findViewById<AppCompatButton>(R.id.btnDeActiveDialog)

        val ivCrossDeactivate=dialog.findViewById<AppCompatImageView>(R.id.ivCrossDeactivate)

        ivCrossDeactivate.setOnClickListener {
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        deActivateBtn.setOnClickListener {
            dialog.dismiss()

        }

        dialog.setCancelable(false)
        dialog.show()

        var cardViewDeActiveProfile=dialog.findViewById<CardView>(R.id.cardViewDeActiveProfile)
        cardViewDeActiveProfile.layoutParams.width=(screenWidth!!.toDouble()/1.1).toInt()
    }



    fun fetchProfileData()
    {
        repository.makeCall(true,
            requestProcessor = object : ApiProcessor<Response<ProfileResponse>> {
                override fun onSuccess(success: Response<ProfileResponse>) {
                    Log.e("Resposne_Dataaaa===", success.body().toString())
                    profileData=success.body()!!.data
                    topProfileVisibility.set(true)
                    nameValue.set(success.body()!!.data.name)
                    userNameValue.set(success.body()!!.data.user_name)
                    emailValue.set(success.body()!!.data.email)
                    aboutValue.set(success.body()!!.data.about)


                  var phoneNumber=  addSpaceBetweenPhoneMethod(success.body()!!.data.mobile_number.toString())

                    phoneValue.set(success.body()!!.data.country_code.toString()+" "+phoneNumber)
                    //phoneCountryValue.set(success.body()!!.data.country_code.toString())
                    /*   var bundle= Bundle()
                       bundle.putString("email",emailFieldValue.get())
                       view.findNavController().navigate(R.id.action_signInFragment_to_signUpCodeFragment,bundle)*/
                }

                override fun onError(message: String) {
                    MainActivity.activity!!.get()!!.showMessage(message)
                }

                override suspend fun sendRequest(retrofitApi: RetrofitAPI): Response<ProfileResponse> {
                    Log.e("mflfldddff==",preferenceFile.fetchStringValue(Constants.LOGIN_USER_ID))
                    return retrofitApi.fetchProfileAPI(preferenceFile.fetchStringValue(Constants.LOGIN_USER_ID))
                }
            })
    }
}