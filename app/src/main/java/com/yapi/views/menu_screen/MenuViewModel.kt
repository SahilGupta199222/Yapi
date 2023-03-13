package com.yapi.views.menu_screen


import android.app.Dialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import com.yapi.MainActivity
import com.yapi.R

class MenuViewModel():ViewModel() {

    var screenWidth:Int?=0
    fun onClick(view:View)
    {
        when(view.id)
        {
           com.yapi.R.id.imgProfilePicCustomerList->{
                val mView: View = LayoutInflater.from(MainActivity.activity!!.get())
                    .inflate(com.yapi.R.layout.menu_popup_options, null, false)
              var newWidth=screenWidth!!/1.5

             //   val popUp = PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false)
                val popUp = PopupWindow(mView, newWidth!!.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT, false)
              // popUp.showAtLocation(mView, Gravity.RIGHT,0,0);
                popUp.isTouchable = true
                popUp.isFocusable = true
                popUp.isOutsideTouchable = true

               //Solution
                popUp.showAsDropDown(view.findViewById(com.yapi.R.id.imgProfilePicCustomerList))

              var constraintsProfile= mView.findViewById<ConstraintLayout>(R.id.constraintsProfile)
               constraintsProfile.setOnClickListener {
                   var dialog=Dialog(MainActivity.activity!!.get()!!)
                   dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent);

                   dialog.setContentView(R.layout.delete_profile_popup)
                   dialog.show()
               }
            }
        }
    }
}