package com.yapi.views.signup_code

import android.view.View
import androidx.lifecycle.ViewModel
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard

class SignupCodeViewModel : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.linearTopSignupCode, R.id.constraintsTopSignupCode -> {
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }
}