package com.yapi.views.menu_screen


import android.app.Dialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
               val btnViewProfile=

               //Solution
                popUp.showAsDropDown(view.findViewById(com.yapi.R.id.imgProfilePicCustomerList))

              var constraintsProfile= mView.findViewById<ConstraintLayout>(R.id.constraintsProfile)
               constraintsProfile.setOnClickListener {
                   popUp.dismiss()
                /*   if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
                       view.findNavController()
                           .navigate(R.id.action_menuFragment_to_profileFragment)
                   }*/
                      }
               var constraintsSettings= mView.findViewById<ConstraintLayout>(R.id.constraintsSettings)
               constraintsSettings.setOnClickListener {
                   popUp.dismiss()
                   /*   if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
                          view.findNavController()
                              .navigate(R.id.action_menuFragment_to_profileFragment)
                      }*/
               }
               var constraintsLogout= mView.findViewById<ConstraintLayout>(R.id.constraintsLogout)
               constraintsLogout.setOnClickListener {
                   popUp.dismiss()
                   /*   if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
                          view.findNavController()
                              .navigate(R.id.action_menuFragment_to_profileFragment)
                      }*/
               }


                   
                   
                   

//                       if (view.findNavController().currentDestination?.id == R.id.menuFragment) {
//                           view.findNavController().navigate(R.id.leaveGroupFragment)
//                       }
//                   val dialog=Dialog(MainActivity.activity!!.get()!!)

//                   dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
//                   dialog.setContentView(R.layout.delete_profile_popup)

//                   dialog.show()
//                   var cardviewDeleteProfile=dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
//                   cardviewDeleteProfile.layoutParams.width=(screenWidth!!.toDouble()/1.1).toInt()

            }

       /*  R.id.layoutAddNewGroupsMenu-> {
            *//* if (view.findNavController().currentDestination?.id == R.id.menuFragment) {

                 view.findNavController()
                     .navigate(R.id.action_menuFragment_to_createGroupFragment)
             }*//*
         }*/
        }
    }
}