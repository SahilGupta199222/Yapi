package com.yapi.views.search_result

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
<<<<<<< HEAD
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.common.hideKeyboard
=======
import com.yapi.R
import com.yapi.common.checkDeviceType
>>>>>>> origin/master

class SearchResultViewModel():ViewModel() {

    var dissmissDialogPopupData= MutableLiveData<Boolean>()
    var cancelshowField= ObservableBoolean(false)

    fun onClick(view: View)
    {
        when(view.id)
        {
<<<<<<< HEAD
            R.id.layoutCreateSearch->{
                MainActivity.activity?.get()?.hideKeyboard()
            }
=======
>>>>>>> origin/master
            R.id.ivOutsideCloseSearchResult,R.id.imgCancelSearch->{
                if(checkDeviceType()){
                    dissmissDialogPopupData.value=true
                }else
                {
                    view.findNavController().navigateUp()
                }
            }
        }
    }
}