package com.yapi.views.add_people_email

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.yapi.R

class ViewModelAddPeopleEmail:ViewModel() {
    var chipGroupAddPeopleEmail:ChipGroup?=null
        fun onClick(view:View){
            when(view.id){
                R.id.imgCancelAddPeopleEmail->{
                    view.findNavController().popBackStack()
                }
                R.id.btnNextGroupAddPeopleEmail->{
                    val list=ArrayList<String>()
                    for (i in 0 until chipGroupAddPeopleEmail?.childCount!!) {
                        val chipView = chipGroupAddPeopleEmail?.getChildAt(i) as Chip
                        val title = chipView.text.toString()
                        list.add(title)
                        Log.d("ChipGroupTitle", title)
                    }
                    val bundle=Bundle()
                    bundle.putStringArrayList("personList",list)
                    if (view.findNavController().currentDestination?.id == R.id.addPeopleEmailFragment) {

                        view.findNavController()
                            .navigate(R.id.action_addPeopleEmailFragment_to_addPeopleEmailConfirmationFragment,
                                bundle)
                    }
                }
                R.id.btnBackAddPeopleEmail->{
                    if (view.findNavController().currentDestination?.id == R.id.addPeopleEmailFragment) {

                        view.findNavController().popBackStack()
                    }
                }
            }
        }
}