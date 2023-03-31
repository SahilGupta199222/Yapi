package com.yapi.views.create_team.second_step_create_team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yapi.databinding.SecondStepCreateTeamBinding
import com.yapi.views.create_team.first_step_create_team.FirstStepViewModel

class SecondStepCreateFragment : Fragment() {
    private lateinit var dataBinding: SecondStepCreateTeamBinding
    val viewModel: SecondStepViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = SecondStepCreateTeamBinding.inflate(LayoutInflater.from(requireActivity()))
        dataBinding.vModel=viewModel
        initUI()
        return dataBinding.root
    }

    private fun initUI() {

    }
}