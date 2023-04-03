package com.yapi.views.userList

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R
import com.yapi.common.checkDeviceType

class UserListViewModel : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.txtuserBack -> {
                if (checkDeviceType()) {

                } else {
                    view.findNavController().popBackStack()
                }
            }
        }
    }
}