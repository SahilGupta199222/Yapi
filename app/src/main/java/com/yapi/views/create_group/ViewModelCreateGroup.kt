package com.yapi.views.create_group

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.common.hideKeyboard
import com.yapi.common.showToastMessage
import com.yapi.views.sign_in.SignInErrorData

class ViewModelCreateGroup : ViewModel() {
    var groupNameValue = ObservableField("")
    var groupNameCount = ObservableField("0/128")

    var groupDescriptionValue = ObservableField("")
    var groupDescriptionCount = ObservableField("0/256")

    var addPeopleScreenOpenData = MutableLiveData<Boolean>()
    var dismissDialogData = MutableLiveData<Boolean>()


    var privateGroupToggle = ObservableBoolean(false)

    var errorData=MutableLiveData<SignInErrorData>()
    var hideKeyboardData=MutableLiveData<Boolean>()

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnCreateGroup -> {
                if (checkValidation()) {
                    errorData.value= SignInErrorData("",0)

                    if (checkDeviceType()) {
                        addPeopleScreenOpenData.value = true
                    } else {
                        view.findNavController()
                            .navigate(R.id.action_createGroupFragment_to_addPeopleFragment)
                    }
                }
            }

            R.id.imgCancelCreateGroup, R.id.ivOutsideCloseGroup -> {
                if (checkDeviceType()) {
                    dismissDialogData.value = true
                } else {
                    if (view.findNavController().currentDestination?.id == R.id.createGroupFragment) {
                        view.findNavController().popBackStack()
                    }
                }
            }
            R.id.topCreateGroupLayout -> {
               //view.context.hideKeyboard()
               // MainActivity.activity!!.get()!!.hideKeyboard()
                hideKeyboardData.value=true
            }
        }
    }

    fun AfterTextChanged(s: CharSequence) {
        if(groupNameValue.get().toString().trim().length>0)
        {
            errorData.value= SignInErrorData("",1)
        }
        groupNameCount.set(groupNameValue.get().toString().trim().length.toString() + "/128")
    }

    fun AfterTextChangedDes(s: CharSequence) {
        if(groupDescriptionCount.get().toString().trim().length>0)
        {
            errorData.value= SignInErrorData("",2)
        }
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

    fun checkValidation(): Boolean {
        if (groupNameValue.get().toString().trim().length == 0) {
           // showToastMessage(MainActivity.activity!!.get()!!.getString(R.string.enter_group_name))
            errorData.value= SignInErrorData(MainActivity.activity!!.get()!!.getString(R.string.enter_group_name),1)
            return false
        } else
            if (groupDescriptionValue.get().toString().trim().length == 0) {
              //  showToastMessage(MainActivity.activity!!.get()!!.getString(R.string.enter_group_description))
                errorData.value= SignInErrorData(MainActivity.activity!!.get()!!.getString(R.string.enter_group_description),2)
                return false
            }
        return true
    }
}