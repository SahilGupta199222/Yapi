package com.yapi.views.create_group

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard

class ViewModelCreateGroup:ViewModel() {
    fun onClick(view:View){
        when(view.id){
            R.id.btnCreateGroup->{
                if (view.findNavController().currentDestination?.id == R.id.createGroupFragment) {

                    view.findNavController()
                        .navigate(R.id.action_createGroupFragment_to_addPeopleFragment)
                }
            }
            R.id.imgCancelCreateGroup->{
                if (view.findNavController().currentDestination?.id == R.id.createGroupFragment) {

                    view.findNavController().popBackStack()
                }
            }
            R.id.layoutCreateGroup ,R.id.layoutScrollViewCreateGroup->{
                //for hide keyboard
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }
}