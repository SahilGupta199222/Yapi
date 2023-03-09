package com.yapi.views.signup

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.yapi.R

class SignupViewModel():ViewModel() {

    var emailValueField=ObservableField("")
    var emailCorrectValue=ObservableBoolean(false)
    fun onClick(view:View)
    {
        when(view.id)
        {
            R.id.btnSignUp->{
                view.findNavController().navigate(R.id.action_signUpFragment2_to_signUpCodeFragment)
            }
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")
        if(s.length==0){
            emailCorrectValue.set(false)
        }else
        {
            emailCorrectValue.set(true)
        }
    }
}