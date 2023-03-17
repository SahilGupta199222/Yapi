package com.yapi.views.signup_code

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yapi.R
import com.yapi.databinding.FragmentSignUpCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpCodeFragment : Fragment() {
    private lateinit var binding:FragmentSignUpCodeBinding
    val viewModel:SignupCodeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSignUpCodeBinding.inflate(LayoutInflater.from(requireActivity()))
        binding.vModel=viewModel
        var email=arguments?.getString("email")
        viewModel.email=email
        binding.txtTempTitleDescriptionSignUpCode.setText(Html.fromHtml(requireActivity().getString(R.string.code_first_part)+"<font color=\"#3d3d3d\"><b>"+email+"</b></font>"+requireActivity().getString(R.string.code_last_part)))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        onClick()
    }

    private fun onClick(){

        binding.apply{

        }
    }
}