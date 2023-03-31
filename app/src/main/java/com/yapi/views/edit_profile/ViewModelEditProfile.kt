package com.yapi.views.edit_profile

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.common.hideKeyboard
import com.yapi.common.isValidEmail

class ViewModelEditProfile:ViewModel() {
    var countryCodeValue=ObservableField("")
    var phoneNumberValue=ObservableField("")
    var dismissDialogData=MutableLiveData<Boolean>()

    var checkTabValid:Boolean?=false
    var constraintsMarginEnd:Int?=0
    fun onClick(view: View) {
        when (view.id) {
        R.id.layoutEditProfile ,R.id.layoutScrollViewEditProfile->{
            //for hide keyboard
            MainActivity.activity!!.get()!!.hideKeyboard()
        }
            R.id.imgCancelEditProfile,R.id.ivOutsideCloseAddPeople->{
                if(checkDeviceType())
                {
                    dismissDialogData.value=true
                }else
                {
                    if(view.findNavController().currentDestination?.id == R.id.editProfileFragment){
                        view.findNavController().popBackStack()
                    }
                }

            }
            R.id.btnDoneEditProfile->{
                if(checkDeviceType())
                {
                    dismissDialogData.value=true
                }else
                {
                    if(view.findNavController().currentDestination?.id == R.id.editProfileFragment){
                        view.findNavController().popBackStack()
                    }
                }
            }
    }
    }

    fun AfterTextChanged(s: CharSequence) {

     /*   if(phoneNumberValue.get().toString().length==4 || phoneNumberValue.get().toString().length==10)
        {
            phoneNumberValue.set(phoneNumberValue.get().toString()+" ")
        }*/
        /*if (emailFieldValue.get().toString().trim().length>0 && isValidEmail(emailFieldValue.get().toString())) {
            emailCorrectValue.set(true)
        } else {
            emailCorrectValue.set(false)
        }*/
    }
}