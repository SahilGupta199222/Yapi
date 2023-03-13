package com.yapi.views.create_group

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R

class ViewModelCreateGroup:ViewModel() {
    fun onClick(view:View){
        when(view.id){
            R.id.btnCreateGroup->{
                view.findNavController().navigate(R.id.action_createGroupFragment_to_addPeopleFragment)
            }
            R.id.imgCancelCreateGroup->{
                view.findNavController().popBackStack()
            }
        }
    }
}