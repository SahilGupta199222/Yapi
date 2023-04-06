package com.yapi.views.signup_code

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard
import com.yapi.common.showToastMessage
import com.yapi.pref.PreferenceFile
import com.yapi.views.sign_in.SignInErrorData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupCodeViewModel @Inject constructor(private var preferenceFile: PreferenceFile) : ViewModel() {
var email:String?=""
    var otpValue=ObservableField<String>()
var errorData=MutableLiveData<SignInErrorData>()
    fun onClick(view: View) {
        when (view.id) {
            R.id.linearTopSignupCode, R.id.constraintsTopSignupCode -> {
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
            R.id.btnSignUpCode->{
                if(view.findNavController().currentDestination?.id==R.id.signUpCodeFragment) {

                    if(otpValue.get().toString().isEmpty() || otpValue.get().toString() == "null" || otpValue.get().toString() == null){
                        errorData.value=SignInErrorData(MainActivity.activity!!.get()!!.getString(R.string.enter_otp),0)
                    }else
                    if(otpValue.get().toString().length==6){
                        errorData.value= SignInErrorData("",0)
                        preferenceFile.saveStringValue("login_email",email.toString())
                        view.findNavController().navigate(R.id.action_signUpCodeFragment_to_signupTeam)
                    }else
                    {
                        errorData.value=SignInErrorData(MainActivity.activity!!.get()!!.getString(R.string.enter_correct_otp),0)
                     //   showToastMessage(MainActivity.activity!!.get()!!.getString(R.string.enter_correct_otp))
                    }
                }
            }
        }
    }
}