package com.yapi.views.signup_code

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yapi.R
import com.yapi.databinding.FragmentSignUpCodeBinding

class SignUpCodeFragment : Fragment() {
    private lateinit var binding:FragmentSignUpCodeBinding
    val viewModel:SignupCodeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSignUpCodeBinding.inflate(LayoutInflater.from(requireActivity()))
        binding.vModel=viewModel
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
           btnSignUpCode.setOnClickListener {
               findNavController().navigate(R.id.action_signUpCodeFragment_to_signupTeam)
           }
        }
    }
}