package com.yapi.views.profile

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R

class ViewModelProfile:ViewModel() {
    fun onClick(view: View){
        when(view.id){
            R.id.btnEditProfile->{
                view.findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
            }
        }
    }
}