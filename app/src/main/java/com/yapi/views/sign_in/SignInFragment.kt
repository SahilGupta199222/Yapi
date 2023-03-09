package com.yapi.views.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yapi.R
import com.yapi.databinding.FragmentSignInBinding
import com.yapi.databinding.FragmentSignUpBinding

class SignInFragment : Fragment() {
    private lateinit var binding:FragmentSignInBinding
     val viewModel:SignInViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSignInBinding.inflate(LayoutInflater.from(requireActivity()))
        binding.vModel=viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {

        }
        onClick()
    }
    private fun onClick(){
     /*   binding.apply {
            btnSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_signUpFragment2)
            }
        }*/
    }
}