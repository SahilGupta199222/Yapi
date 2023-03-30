package com.yapi.views.add_people

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.common.hideKeyboard

class ViewModelAddPeople : ViewModel() {
    var addPeopleEmailScreenOpenData= MutableLiveData<Boolean>()
    var dismissDialogData=MutableLiveData<Boolean>()
    fun onClick(view: View) {
        when (view.id) {
            R.id.imgCancelAddPeople,R.id.btnBack,R.id.ivOutsideCloseAddPeople -> {
                if(checkDeviceType()){
                    dismissDialogData.value=true
                }else
                {
                view.findNavController().popBackStack()
            }
            }
            R.id.layoutSendEmailInvitationAddPeople->{

                if(checkDeviceType()){
                    addPeopleEmailScreenOpenData.value=true
                }else
                {
                    view.findNavController().navigate(R.id.action_addPeopleFragment_to_addPeopleEmailFragment)
                }
            }
            R.id.btnNext->{
                if(checkDeviceType()){
                    addPeopleEmailScreenOpenData.value=true
                }else
                {
                    view.findNavController().navigate(R.id.action_addPeopleFragment_to_addPeopleEmailFragment)
                }
            }
            R.id.topConstraintsAddPeople->{
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }
}