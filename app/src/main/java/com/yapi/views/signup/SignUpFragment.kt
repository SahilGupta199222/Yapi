package com.yapi.views.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yapi.R
import com.yapi.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var binding:FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentSignUpBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {

        }
    }
}