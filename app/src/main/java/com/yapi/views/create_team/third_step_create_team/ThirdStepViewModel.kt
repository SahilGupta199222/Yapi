package com.yapi.views.create_team.third_step_create_team

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard
import com.yapi.databinding.CrmDialogLayoutBinding

class ThirdStepViewModel : ViewModel() {
    var screenWidth: Int? = 0
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnThirdCreateTeam -> {
                if (view.findNavController().currentDestination?.id == R.id.thirdStepCreateTeam) {

                    view.findNavController()
                        .navigate(R.id.action_thirdStepCreateTeam_to_chatEmptyFragment)
                }
            }
            R.id.tvSkipStep -> {
                if (view.findNavController().currentDestination?.id == R.id.thirdStepCreateTeam) {
                    view.findNavController()
                        .navigate(R.id.action_thirdStepCreateTeam_to_chatEmptyFragment)
                }
            }
            R.id.linearTopThirdStep, R.id.constraintsTopThirdStep -> {
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
            R.id.btnInviteCRM -> {
                //show CRM Invite Dialog
                showCRMDialog()

            }
        }
    }

    private fun showCRMDialog() {
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        var databinding =
            CrmDialogLayoutBinding.inflate(LayoutInflater.from(MainActivity.activity!!.get()!!))
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(databinding.root)
        dialog.show()
        var cardviewCRMInvite = dialog.findViewById<CardView>(R.id.cardviewCRMInvite)
        cardviewCRMInvite.layoutParams.width = (screenWidth!!.toDouble() / 1.1).toInt()

        databinding.btnCancelInvite.setOnClickListener {
            dialog.dismiss()
        }
        databinding.btnInviteCRM.setOnClickListener { }
    }
}