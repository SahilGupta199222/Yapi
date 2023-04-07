package com.yapi.views.create_team.third_step_create_team

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yapi.R
import com.yapi.common.changeBackgroundForEditError
import com.yapi.common.changeBackgroundForError
import com.yapi.databinding.SecondStepCreateTeamBinding
import com.yapi.databinding.ThirdStepCreateLayoutBinding
import com.yapi.views.create_team.second_step_create_team.SecondStepViewModel
import com.yapi.views.sign_in.SignInErrorData
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
        showErrorUIObserver()
    }

    fun showErrorUIObserver()
    {
        viewModel.errorData.observe(requireActivity(), Observer {
            var data=it as SignInErrorData
            if(data!=null && data.message.isNotEmpty())
            {
                dataBinding.txtErrorEmailSignup!!.setText(data.message)
                changeBackgroundForEditError(dataBinding.etMemberEmail,requireActivity().resources.getColor(
                    R.color.error_box_color),
                    requireActivity().resources.getColor(R.color.error_border_color))
            }else
            {
                dataBinding.txtErrorEmailSignup!!.setText("")
                changeBackgroundForEditError(dataBinding.etMemberEmail,requireActivity().resources.getColor(
                    R.color.white),
                    requireActivity().resources.getColor(R.color.liteGrey))
            }
        })
    }
}


