package com.yapi.views.create_team.first_step_create_team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yapi.databinding.FirstStepCreateFragmentBinding

class FirstStepCreateFragment : Fragment() {
    private lateinit var dataBinding: FirstStepCreateFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = FirstStepCreateFragmentBinding.inflate(LayoutInflater.from(requireActivity()))
        initUI()

        return dataBinding.root
    }

    private fun initUI() {

    }
}