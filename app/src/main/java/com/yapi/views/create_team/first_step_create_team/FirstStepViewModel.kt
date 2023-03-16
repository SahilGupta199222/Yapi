package com.yapi.views.create_team.first_step_create_team

import android.text.Html
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
import com.yapi.pref.PreferenceFile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstStepViewModel @Inject constructor(private var preferenceFile: PreferenceFile) : ViewModel() {

    var teamName = ObservableField("")
    var nameCountValue = ObservableField("0/50")
    var checkBoxValue = ObservableBoolean(false)
var checkBoxTextValue=ObservableField("")
    init {
  /*    var email=  preferenceFile.fetchStringValue("login_email")
       var firstText= MainActivity.activity?.get()?.resources?.getString(R.string.create_team_first_text)
       var lastText= MainActivity.activity?.get()?.resources?.getString(R.string.create_team_last_text)
     var checkBoxText=  Html.fromHtml(MainActivity.activity?.get()?.getString(R.string.create_team_first_text)+"<font color='#3d3d3d'>"+email+"</font> "+MainActivity.activity?.get()?.getString(R.string.create_team_last_text))
        checkBoxTextValue.set(checkBoxText.toString())*/
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnFirstCreateTeam -> {
                Log.e("Hello_Text==", "Helloo")
                // showMessage("Hello")
                if (checkValidation()) {
                    if(view.findNavController().currentDestination?.id==R.id.firstStepCreateTeam) {

                        view.findNavController()
                            .navigate(R.id.action_firstStepCreateTeam_to_secondStepCreateTeam)
                    }
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