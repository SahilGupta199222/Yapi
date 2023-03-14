package com.yapi.views.create_team.first_step_create_team

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard

class FirstStepViewModel : ViewModel() {

    var teamName = ObservableField("")
    var nameCountValue = ObservableField("0/50")
    var checkBoxValue = ObservableBoolean(false)

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnFirstCreateTeam -> {
                Log.e("Hello_Text==", "Helloo")
                // showMessage("Hello")
                if (checkValidation()) {
                    view.findNavController()
                        .navigate(R.id.action_firstStepCreateTeam_to_secondStepCreateTeam)
                } else {
                    Toast.makeText(view.context, "Please enter company name", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            R.id.linearTopFirstStep, R.id.constraintsTopFirstStep -> {
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    private fun checkValidation(): Boolean {
        return !teamName.get().toString().trim().isEmpty()
//            checkBoxValue.get()
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")
        nameCountValue.set(s.length.toString() + "/50")

    }
}