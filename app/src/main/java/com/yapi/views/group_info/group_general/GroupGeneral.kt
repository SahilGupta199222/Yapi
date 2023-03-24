package com.yapi.views.group_info.group_general

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yapi.R
import com.yapi.common.hideKeyboard
import com.yapi.databinding.FragmentGroupGeneralBinding

class GroupGeneral : Fragment() {
    private lateinit var binding:FragmentGroupGeneralBinding
    private var editGroupOpenStatus=true
    private val vModel:ViewModelGroupGeneral by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentGroupGeneralBinding.inflate(LayoutInflater.from(requireContext()))
        binding.model=vModel
        binding.includeGroupGnlInfo.model=vModel
        binding.includeGroupGnlInfoEdit.model=vModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeLayout()
        clickListner()
    }

    private fun clickListner() {
        binding.apply {
            btnDoneGroupGeneral.setOnClickListener {
                editGroupOpenStatus=!editGroupOpenStatus
                changeLayout()
            }
            /*includeGroupGnlInfoEdit.layoutGroupGnlInfoEdit.setOnClickListener {
                //forHide Keyboard
                requireActivity().hideKeyboard()
            }
            includeGroupGnlInfo.layoutGroupGnlInfo.setOnClickListener {
                //forHide Keyboard
                requireActivity().hideKeyboard()
            }*/
        }
    }

    private fun changeLayout() {
        binding.apply {
            if (editGroupOpenStatus) {
                btnDoneGroupGeneral.setText(requireActivity().getString(R.string.edit_group))
                includeGroupGnlInfoEdit.layoutGroupGnlInfoEdit.visibility=View.INVISIBLE
                includeGroupGnlInfo.layoutGroupGnlInfo.visibility=View.VISIBLE
            } else {
                btnDoneGroupGeneral.setText(requireActivity().getString(R.string.done))
                includeGroupGnlInfo.layoutGroupGnlInfo.visibility=View.INVISIBLE
                includeGroupGnlInfoEdit.layoutGroupGnlInfoEdit.visibility=View.VISIBLE
            }
        }
    }
}