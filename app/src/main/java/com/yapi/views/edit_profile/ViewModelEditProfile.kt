package com.yapi.views.edit_profile

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard

class ViewModelEditProfile:ViewModel() {
    var countryCodeValue=ObservableField("")
    var phoneNumberValue=ObservableField("")
    fun onClick(view: View) {
        when (view.id) {
        R.id.layoutEditProfile ,R.id.layoutScrollViewEditProfile->{
            //for hide keyboard
            MainActivity.activity!!.get()!!.hideKeyboard()
        }
            R.id.imgCancelEditProfile->{
                if(view.findNavController().currentDestination?.id == R.id.editProfileFragment){
                    view.findNavController().popBackStack()
                }
            }
            R.id.btnDoneEditProfile->{
                if(view.findNavController().currentDestination?.id == R.id.editProfileFragment){
                    view.findNavController().popBackStack()
                }
            }
    }
    }
}