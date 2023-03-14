package com.yapi.views.add_people

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard

class ViewModelAddPeople : ViewModel() {
    fun onClick(view: View) {
        when (view.id) {
            R.id.imgCancelAddPeople,R.id.btnBack -> {
                view.findNavController().popBackStack()
            }
            R.id.layoutSendEmailInvitationAddPeople->{
                view.findNavController().navigate(R.id.action_addPeopleFragment_to_addPeopleEmailFragment)
            }
            R.id.btnNext->{
                view.findNavController().navigate(R.id.action_addPeopleFragment_to_addPeopleEmailFragment)
            }
            R.id.topConstraintsAddPeople->{
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }
}