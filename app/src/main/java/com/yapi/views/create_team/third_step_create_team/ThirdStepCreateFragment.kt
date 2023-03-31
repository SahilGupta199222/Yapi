package com.yapi.views.create_team.third_step_create_team

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yapi.databinding.SecondStepCreateTeamBinding
import com.yapi.databinding.ThirdStepCreateLayoutBinding
import com.yapi.views.create_team.second_step_create_team.SecondStepViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdStepCreateFragment : Fragment() {
    private lateinit var dataBinding: ThirdStepCreateLayoutBinding
    private val viewModel: ThirdStepViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = ThirdStepCreateLayoutBinding.inflate(LayoutInflater.from(requireActivity()))
        dataBinding.vModel=viewModel
        initUI()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels
        viewModel.screenWidth=width
        return dataBinding.root
    }

    private fun initUI() {

    }
}


