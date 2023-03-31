package com.yapi.views.signup

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard
import com.yapi.common.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel


class SignupViewModel : ViewModel() {

    var emailValueField = ObservableField("")
    var emailCorrectValue = ObservableBoolean(false)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSignUp -> {
                if(view.findNavController().currentDestination?.id==R.id.signUpFragment2) {
                    var bundle= Bundle()
                    bundle.putString("email",emailValueField.get())
                    view.findNavController()
                        .navigate(R.id.action_signUpFragment2_to_signUpCodeFragment,bundle)
                }
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

    fun AfterTextChanged(s: Editable?) {
        if (emailValueField.get().toString().trim().length > 0 && isValidEmail(emailValueField.get()
                .toString())
        ) {
            emailCorrectValue.set(true)
        } else {
            emailCorrectValue.set(false)
        }
    }
}