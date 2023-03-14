package com.yapi.views.add_people_email_confirmation

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R

class ViewModelAddPeopleEmailConfirmation:ViewModel(){
    fun onClick(view:View){
        when(view.id){
            R.id.imgCancelAddPeopleEmailConf->{
                if (view.findNavController().currentDestination?.id == R.id.addPeopleEmailConfirmationFragment) {

                    view.findNavController().popBackStack()
                }
            }
            R.id.btnDoneGroupAddPeopleEmailConfirm->{
                if (view.findNavController().currentDestination?.id == R.id.addPeopleEmailConfirmationFragment) {

                    view.findNavController().popBackStack()
                }
            }
        }
    }
}