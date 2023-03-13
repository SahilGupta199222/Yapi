package com.yapi.views.add_people_email_confirmation

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R

class ViewModelAddPeopleEmailConfirmation:ViewModel(){
    fun onClick(view:View){
        when(view.id){
            R.id.imgCancelAddPeopleEmailConf->{
                view.findNavController().popBackStack()
            }
            R.id.btnDoneGroupAddPeopleEmailConfirm->{
                view.findNavController().popBackStack()
            }
        }
    }
}