package com.yapi.views.sign_in

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
                    view.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment2)
                }
            }
            R.id.txtSignIn -> {
                view.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment2)
            }
            R.id.linearTopSignIn, R.id.constraintsTopSignIN -> {
                //for hide keyboard
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    private fun checkValidation(): Boolean {
        if (emailFieldValue.get().toString().isEmpty()) {
            showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_email))
            return false
        } else if (!(isValidEmail(emailFieldValue.get().toString()))) {
            showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_valid_email))
            return false
        } else if (passwordFieldValue.get().toString().isEmpty()) {
            showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_password))
            return false
        } else {
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

    fun AfterTextChanged(s: CharSequence) {
     if (emailFieldValue.get().toString().trim().length>0 && isValidEmail(emailFieldValue.get().toString())) {
            emailCorrectValue.set(true)
        } else {
            emailCorrectValue.set(false)
        }
    }
}