package com.yapi.views.signup

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard
import com.yapi.common.isValidEmail
import com.yapi.views.sign_in.SignInErrorData
import dagger.hilt.android.lifecycle.HiltViewModel


class SignupViewModel : ViewModel() {

    var emailValueField = ObservableField("")
    var emailCorrectValue = ObservableBoolean(false)
    var errorData=MutableLiveData<SignInErrorData>()
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSignUp -> {
                if(view.findNavController().currentDestination?.id==R.id.signUpFragment2) {
                    if (checkValidation()) {
                        var bundle = Bundle()
                        bundle.putString("email", emailValueField.get())
                        view.findNavController()
                            .navigate(R.id.action_signUpFragment2_to_signUpCodeFragment, bundle)
                    }
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
            errorData.value= SignInErrorData("",0)
        } else {
            emailCorrectValue.set(false)
        }
    }

    private fun checkValidation(): Boolean {
        if (emailValueField.get().toString().isEmpty()) {
            //  showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_email))
            errorData.value=SignInErrorData(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_email))
            return false
        } else if (!(isValidEmail(emailValueField.get().toString()))) {
            //  showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_valid_email))
            errorData.value=SignInErrorData(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_valid_email))
            return false
        } else {
            errorData.value=SignInErrorData("")
            return true
        }
    }
}