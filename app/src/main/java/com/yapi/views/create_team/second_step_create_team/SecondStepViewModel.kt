package com.yapi.views.create_team.second_step_create_team

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard

class SecondStepViewModel() : ViewModel() {

    var countFieldValue=ObservableField("0/50")
    var teamNameValue=ObservableField("")
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSecondCreateTeam -> {
                Log.e("Hello_Text==","Helloo")
                // showMessage("Hello")
                view.findNavController().navigate(R.id.action_secondStepCreateTeam_to_thirdStepCreateTeam)
            }
            R.id.linearTopSecondStep,R.id.constrantsTopSecondStep->{
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")
        countFieldValue.set(s.length.toString()+"/50")
    }
}