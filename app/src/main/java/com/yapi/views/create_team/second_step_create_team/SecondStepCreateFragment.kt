package com.yapi.views.create_team.second_step_create_team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yapi.databinding.SecondStepCreateTeamBinding

class SecondStepCreateFragment : Fragment() {
    private lateinit var dataBinding: SecondStepCreateTeamBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = SecondStepCreateTeamBinding.inflate(LayoutInflater.from(requireActivity()))
        initUI()
        return dataBinding.root
    }

    private fun initUI() {

    }
}