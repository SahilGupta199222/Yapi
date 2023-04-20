package com.yapi.views.create_team.third_step_create_team

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.Constants
import com.yapi.common.MyMessageEvent
import com.yapi.common.hideKeyboard
import com.yapi.common.isValidEmail
import com.yapi.databinding.CrmDialogLayoutBinding
import com.yapi.pref.PreferenceFile
import com.yapi.views.sign_in.SignInErrorData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@HiltViewModel
class ThirdStepViewModel @Inject constructor(val preferenceFile: PreferenceFile) : ViewModel() {
    var screenWidth: Int? = 0
    var emailFieldValue = ObservableField("")
    var errorData = MutableLiveData<SignInErrorData>()

    var teamId: String? = ""
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnThirdCreateTeam -> {
                if (view.findNavController().currentDestination?.id == R.id.thirdStepCreateTeam) {
                    if (checkValidation()) {
                        errorData.value = SignInErrorData("", 0)
                        preferenceFile.saveStringValue(Constants.USER_ID, "1")
                        if (MainActivity.activity!!.get()!!.resources
                                .getBoolean(R.bool.isTab)
                        ) {
                            System.out.println("phone========tablet")
                            Log.e("gsegegsgsgs111===", System.currentTimeMillis().toString())

                            EventBus.getDefault().post(MyMessageEvent(1, Constants.MENU_KEY)) //post
                            // event
                        } else {
                            view.findNavController()
                                .navigate(R.id.action_thirdStepCreateTeam_to_menuFragment)
                        }
                    }
                }
            }
            R.id.tvSkipStep -> {
                if (view.findNavController().currentDestination?.id == R.id.thirdStepCreateTeam) {
                    preferenceFile.saveStringValue(Constants.USER_ID, "1")
                    if (MainActivity.activity!!.get()!!.resources.getBoolean(R.bool.isTab)) {
                        System.out.println("phone========tablet")
                        Log.e("gsegegsgsgs111===", System.currentTimeMillis().toString())

                        EventBus.getDefault()
                            .post(MyMessageEvent(1, Constants.MENU_KEY)) //post event
                    } else {
                        view.findNavController()
                            .navigate(R.id.action_thirdStepCreateTeam_to_menuFragment)
                    }
                }
            }
            R.id.linearTopThirdStep, R.id.constraintsTopThirdStep -> {
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
            R.id.btnInviteCRM -> {
                //show CRM Invite Dialog
                showCRMDialog()
            }
            R.id.constraintsAddMember -> {
                //for Add Member
                val bundle = Bundle()
                if (Constants.API_CALL_DEMO) {
                    bundle.putString("team_id", teamId)
                }
                view.findNavController()
                    .navigate(R.id.action_thirdStepCreateTeam_to_addPeopleFragment, bundle)
            }
        }
    }

    //show CRM Dialog
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

    private fun checkValidation(): Boolean {
        if (emailFieldValue.get().toString().isEmpty()) {
            //  showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_email))
            errorData.value =
                SignInErrorData(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_email),
                    0)
            return false
        } else if (!(isValidEmail(emailFieldValue.get().toString()))) {
            // showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_valid_email))
            errorData.value =
                SignInErrorData(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_valid_email),
                    0)

            return false
        } else {
            return true
        }
    }

    fun AfterTextChanged(s: Editable?) {
        if (emailFieldValue.get().toString().trim().length > 0 && isValidEmail(emailFieldValue.get()
                .toString())
        ) {
            errorData.value = SignInErrorData("", 0)
        } else {
        }
    }
}