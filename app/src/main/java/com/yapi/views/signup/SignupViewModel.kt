package com.yapi.views.signup

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard
import com.yapi.common.isValidEmail

class SignupViewModel : ViewModel() {

    var emailValueField = ObservableField("")
    var emailCorrectValue = ObservableBoolean(false)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSignUp -> {
                view.findNavController().navigate(R.id.action_signUpFragment2_to_signUpCodeFragment)
            }
            R.id.linearTopSignup, R.id.constarintsTopSignup -> {
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        /*Log.w("tag", "onTextChanged $s")
        if(s.length==0){
            emailCorrectValue.set(false)
        }else
        {
            emailCorrectValue.set(true)
        }*/
    }

    fun AfterTextChanged(s: CharSequence) {
        if (emailValueField.get().toString().trim().length > 0 && isValidEmail(emailValueField.get()
                .toString())
        ) {
            emailCorrectValue.set(true)
        } else {
            emailCorrectValue.set(false)
        }
    }
}