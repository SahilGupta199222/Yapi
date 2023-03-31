package com.yapi.views.search

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R
import com.yapi.common.checkDeviceType

class SearchViewModel():ViewModel() {

    fun onClick(view:View){
        when(view.id)
        {
            R.id.imgCancelSearch->{
                if(checkDeviceType()){

                }else
                {
                    view.findNavController().navigateUp()
                }
            }
        }
    }
}