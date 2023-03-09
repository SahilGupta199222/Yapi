package com.yapi.views.create_team.second_step_create_team

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R

class SecondStepViewModel() : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSecondCreateTeam -> {
                Log.e("Hello_Text==","Helloo")
                // showMessage("Hello")
                view.findNavController().navigate(R.id.action_secondStepCreateTeam_to_thirdStepCreateTeam)
            }
        }
    }
}