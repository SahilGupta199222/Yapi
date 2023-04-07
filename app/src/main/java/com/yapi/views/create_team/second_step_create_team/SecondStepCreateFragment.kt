package com.yapi.views.create_team.second_step_create_team

import android.os.Bundle
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
import com.yapi.views.create_team.first_step_create_team.FirstStepViewModel
import com.yapi.views.sign_in.SignInErrorData

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
        showErrorUIObserver()
    }

    fun showErrorUIObserver()
    {
        viewModel.errorData.observe(requireActivity(), Observer {
            var data=it as SignInErrorData
            if(data!=null && data.message.isNotEmpty())
            {
                dataBinding.txtErrorEmailSecond!!.setText(data.message)
                changeBackgroundForEditError(dataBinding.etTeamName,requireActivity().resources.getColor(
                    R.color.error_box_color),
                    requireActivity().resources.getColor(R.color.error_border_color))
            }else
            {
                dataBinding.txtErrorEmailSecond!!.setText("")
                changeBackgroundForEditError(dataBinding.etTeamName,requireActivity().resources.getColor(
                    R.color.white),
                    requireActivity().resources.getColor(R.color.liteGrey))
            }
        })
    }
}