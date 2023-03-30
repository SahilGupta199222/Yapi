package com.yapi.views.create_group

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.common.hideKeyboard
import com.yapi.views.add_people.AddPeopleFragment

class ViewModelCreateGroup : ViewModel() {
    var groupNameValue = ObservableField("")
    var groupNameCount = ObservableField("0/128")

    var groupDescriptionValue = ObservableField("")
    var groupDescriptionCount = ObservableField("0/256")

    var addPeopleScreenOpenData=MutableLiveData<Boolean>()
    var dismissDialogData=MutableLiveData<Boolean>()
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnCreateGroup -> {
                if (checkDeviceType()) {
                    addPeopleScreenOpenData.value=true
                } else {
                    view.findNavController()
                        .navigate(R.id.action_createGroupFragment_to_addPeopleFragment)
                }
            }

            R.id.imgCancelCreateGroup,R.id.ivOutsideCloseGroup -> {
                if(checkDeviceType()){
                    dismissDialogData.value=true
                }else
                {
                    if (view.findNavController().currentDestination?.id == R.id.createGroupFragment) {
                        view.findNavController().popBackStack()
                    }
                }
            }
            R.id.topCreateGroupLayout -> {
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    fun AfterTextChanged(s: CharSequence) {
        groupNameCount.set(groupNameValue.get().toString().trim().length.toString() + "/128")
    }

    fun AfterTextChangedDes(s: CharSequence) {
        groupDescriptionCount.set(groupDescriptionValue.get().toString()
            .trim().length.toString() + "/256")
    }


    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")


        /*   if (s.length == 0) {
               emailCorrectValue.set(false)
           } else if (emailFieldValue.get().toString().trim().length>0 && isValidEmail(emailFieldValue.get().toString())) {
               emailCorrectValue.set(true)
           } else {
               emailCorrectValue.set(false)
           }*/
    }

}