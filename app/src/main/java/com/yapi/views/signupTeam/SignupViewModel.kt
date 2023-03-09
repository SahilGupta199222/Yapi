package com.yapi.views.signupTeam

import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R

class SignupViewModel():ViewModel() {
    fun onClick(view: View){
        when(view.id){
            R.id.craeteTeamBtn ->{
                view.findNavController().navigate(R.id.action_signupTeam_to_firstStepCreateTeam)
            }
        }
    }

}