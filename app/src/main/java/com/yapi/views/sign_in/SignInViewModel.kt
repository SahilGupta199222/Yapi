package com.yapi.views.sign_in

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard
import com.yapi.common.isValidEmail
import com.yapi.common.showToastMessage

class SignInViewModel : ViewModel() {

    var emailFieldValue = ObservableField("")
    var passwordFieldValue = ObservableField("")
    var emailCorrectValue = ObservableBoolean(false)

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSignIn -> {
                if (checkValidation()) {
                    if(view.findNavController().currentDestination?.id==R.id.signInFragment) {
//                        view.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment2)
                       // view.findNavController().navigate(R.id.action_signInFragment_to_chatEmptyFragment)
                        var bundle= Bundle()
                        bundle.putString("email",emailFieldValue.get())
                        view.findNavController().navigate(R.id.action_signInFragment_to_signUpCodeFragment,bundle)
                    }
                }
            }
            R.id.txtSignIn -> {
                if(view.findNavController().currentDestination?.id==R.id.signInFragment) {
                    view.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment2)
                }
            }
            R.id.linearTopSignIn, R.id.constraintsTopSignIN -> {
                //for hide keyboard
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }
    fun onLongg(view:View):Boolean{
        when (view.id) {
            R.id.btnSignIn -> {
                    if(view.findNavController().currentDestination?.id==R.id.signInFragment) {
//                        view.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment2)
                        view.findNavController().navigate(R.id.action_signInFragment_to_chipSetDemoFragment)
                    }
                }
            }
        return  true
    }

    private fun checkValidation(): Boolean {
        if (emailFieldValue.get().toString().isEmpty()) {
            showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_email))
            return false
        } else if (!(isValidEmail(emailFieldValue.get().toString()))) {
            showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_valid_email))
            return false
        } /*else if (passwordFieldValue.get().toString().isEmpty()) {
            showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_password))
            return false
        } */else {
            return true
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")
     /*   if (s.length == 0) {
            emailCorrectValue.set(false)
        } else if (emailFieldValue.get().toString().trim().length>0 && isValidEmail(emailFieldValue.get().toString())) {
            emailCorrectValue.set(true)
        } else {
            emailCorrectValue.set(false)
        }*/
    }

    fun AfterTextChanged(s: Editable?) {
     if (emailFieldValue.get().toString().trim().length>0 && isValidEmail(emailFieldValue.get().toString())) {
            emailCorrectValue.set(true)
        } else {
            emailCorrectValue.set(false)
        }
    }
}