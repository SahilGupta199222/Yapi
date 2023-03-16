package com.yapi.views.signup_code

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard
import com.yapi.pref.PreferenceFile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupCodeViewModel @Inject constructor(private var preferenceFile: PreferenceFile) : ViewModel() {
var email:String?=""
    fun onClick(view: View) {
        when (view.id) {
            R.id.linearTopSignupCode, R.id.constraintsTopSignupCode -> {
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
            R.id.btnSignUpCode->{
                if(view.findNavController().currentDestination?.id==R.id.signUpCodeFragment) {
                    preferenceFile.saveStringValue("login_email",email.toString())
                    view.findNavController().navigate(R.id.action_signUpCodeFragment_to_signupTeam)
                }
            }
        }
    }
}