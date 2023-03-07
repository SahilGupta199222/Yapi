package com.yapi.views.create_team.first_step_create_team

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R
import com.yapi.common.showMessage

class FirstStepViewModel() : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnFirstCreateTeam -> {
                Log.e("Hello_Text==","Helloo")
               // showMessage("Hello")
          //  view.findNavController().navigate(R.id.action_firstStepCreateTeam_to_secondStepCreateTeam)
            }
        }
    }
}