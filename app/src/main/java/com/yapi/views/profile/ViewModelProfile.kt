package com.yapi.views.profile

import android.app.Dialog
import android.view.View
import android.view.ViewGroup.LayoutParams
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard

class ViewModelProfile : ViewModel() {

    var screenWidth:Int?=0
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnEditProfile -> {
                if (view.findNavController().currentDestination?.id == R.id.profileFragment) {
                    view.findNavController()
                        .navigate(R.id.action_profileFragment_to_editProfileFragment)
                }
            }
            R.id.layoutDeleteAccountProfile->{
                deleteAccountDialog(view)
            }
            R.id.layoutDeActiveProfile->{
                deActiveAccountDialog(view)
            }
            R.id.imgCancelProfile-> {
                if (view.findNavController().currentDestination?.id == R.id.profileFragment) {
                    view.findNavController().popBackStack()
                }
            }
            R.id.layoutProfile,R.id.layoutScrollViewProfile->{
                //for hide keyboard
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    private fun deleteAccountDialog(view:View) {
        val dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.delete_profile_popup)
//        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        val cancelBtn=dialog.findViewById<AppCompatButton>(R.id.btnProfileCancel)
        val deleteBtn=dialog.findViewById<AppCompatButton>(R.id.btnProfileDelete)
        cancelBtn.setOnClickListener {
            dialog.dismiss()

        }
        deleteBtn.setOnClickListener {
            dialog.dismiss()

        }
        dialog.setCancelable(false)
        dialog.show()

        var cardviewDeleteProfile=dialog.findViewById<CardView>(R.id.cardviewDeleteProfile)
        cardviewDeleteProfile.layoutParams.width=(screenWidth!!.toDouble()/1.1).toInt()
    }
    private fun deActiveAccountDialog(view:View){
        val dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.dialog_de_activie_profile)
//        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        val cancelBtn=dialog.findViewById<AppCompatButton>(R.id.btnCancelDeActiveDialog)
        val deActivateBtn=dialog.findViewById<AppCompatButton>(R.id.btnDeActiveDialog)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        deActivateBtn.setOnClickListener {
            dialog.dismiss()

        }

        dialog.setCancelable(false)
        dialog.show()


        var cardViewDeActiveProfile=dialog.findViewById<CardView>(R.id.cardViewDeActiveProfile)
        cardViewDeActiveProfile.layoutParams.width=(screenWidth!!.toDouble()/1.1).toInt()
    }
}