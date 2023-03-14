package com.yapi.views.edit_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yapi.R
import com.yapi.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {
    private lateinit var binding:FragmentEditProfileBinding
    private val viewModel:ViewModelEditProfile by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentEditProfileBinding.inflate(LayoutInflater.from(requireContext()))
        binding.model=viewModel
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