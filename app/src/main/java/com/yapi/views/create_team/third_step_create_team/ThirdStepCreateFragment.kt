package com.yapi.views.create_team.third_step_create_team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yapi.databinding.SecondStepCreateTeamBinding
import com.yapi.databinding.ThirdStepCreateLayoutBinding

class ThirdStepCreateFragment : Fragment() {
    private lateinit var dataBinding: ThirdStepCreateLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = ThirdStepCreateLayoutBinding.inflate(LayoutInflater.from(requireActivity()))
        initUI()

        return dataBinding.root
    }

    private fun initUI() {

    }
}