package com.yapi.views.create_team.first_step_create_team

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R

class FirstStepViewModel() : ViewModel() {

     var teamName=ObservableField("")
     var nameCountValue=ObservableField("0/50")
    var checkBoxValue=ObservableBoolean(false)

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnFirstCreateTeam -> {
                Log.e("Hello_Text==","Helloo")
               // showMessage("Hello")
                if(checkValidation()){
                    view.findNavController().navigate(R.id.action_firstStepCreateTeam_to_secondStepCreateTeam)
                }
            }
        }
    }

    private fun checkValidation():Boolean
    {
        return if(teamName.get().toString().trim().isEmpty()) {
            false
        }else
            checkBoxValue.get()
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")
        nameCountValue.set(s.length.toString()+"/50")

    }
}