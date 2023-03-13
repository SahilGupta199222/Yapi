package com.yapi.views.add_people

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R

class ViewModelAddPeople : ViewModel() {
    fun onClick(view: View) {
        when (view.id) {

            R.id.imgCancelAddPeople -> {
                view.findNavController().popBackStack()
            }
        }
    }

}