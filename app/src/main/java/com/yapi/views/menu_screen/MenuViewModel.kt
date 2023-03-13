package com.yapi.views.menu_screen

import android.R
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity



class MenuViewModel():ViewModel() {

    fun onClick(view:View)
    {
        when(view.id)
        {
           com.yapi.R.id.imgProfilePicCustomerList->{
                val mView: View = LayoutInflater.from(MainActivity.activity!!.get())
                    .inflate(com.yapi.R.layout.menu_popup_options, null, false)
             //   val popUp = PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false)
                val popUp = PopupWindow(mView, 400, LinearLayout.LayoutParams.WRAP_CONTENT, false)
                popUp.isTouchable = true
                popUp.isFocusable = true
                popUp.isOutsideTouchable = true
               val btnViewProfile=

               //Solution
                popUp.showAsDropDown(view.findViewById(com.yapi.R.id.imgProfilePicCustomerList))
            }
            com.yapi.R.id.layoutAddNewGroupsMenu->{
                view.findNavController().navigate(com.yapi.R.id.action_menuFragment_to_createGroupFragment)
            }
        }
    }
}