package com.yapi.views.create_team.third_step_create_team

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard

class ThirdStepViewModel() : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnThirdCreateTeam -> {
                if(view.findNavController().currentDestination?.id==R.id.thirdStepCreateTeam) {

                    view.findNavController()
                        .navigate(R.id.action_thirdStepCreateTeam_to_chatEmptyFragment)
                }
            }
            R.id.linearTopThirdStep,R.id.constraintsTopThirdStep->{
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }
}