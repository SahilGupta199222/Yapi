package com.yapi.views.create_group

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.hideKeyboard

class ViewModelCreateGroup:ViewModel() {
    fun onClick(view:View){
        when(view.id){
            R.id.btnCreateGroup->{
                if (view.findNavController().currentDestination?.id == R.id.createGroupFragment) {

                    view.findNavController()
                        .navigate(R.id.action_createGroupFragment_to_addPeopleFragment)
                }
class ViewModelCreateGroup : ViewModel() {
    var groupNameValue = ObservableField("")
    var groupNameCount = ObservableField("0/128")

    var groupDescriptionValue = ObservableField("")
    var groupDescriptionCount = ObservableField("0/256")
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnCreateGroup -> {
                view.findNavController()
                    .navigate(R.id.action_createGroupFragment_to_addPeopleFragment)
            }
            R.id.imgCancelCreateGroup -> {
                view.findNavController().popBackStack()
            R.id.imgCancelCreateGroup->{
                if (view.findNavController().currentDestination?.id == R.id.createGroupFragment) {

                    view.findNavController().popBackStack()
                }
            }
            R.id.topCreateGroupLayout->{
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    fun AfterTextChanged(s: CharSequence) {
        groupNameCount.set(groupNameValue.get().toString().trim().length.toString() + "/128")
    }

    fun AfterTextChangedDes(s: CharSequence) {
        groupDescriptionCount.set(groupDescriptionValue.get().toString()
            .trim().length.toString() + "/256")
    }


    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")


        /*   if (s.length == 0) {
               emailCorrectValue.set(false)
           } else if (emailFieldValue.get().toString().trim().length>0 && isValidEmail(emailFieldValue.get().toString())) {
               emailCorrectValue.set(true)
           } else {
               emailCorrectValue.set(false)
           }*/
    }

}