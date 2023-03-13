package com.yapi.views.profile

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.yapi.R
import com.yapi.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding:com.yapi.databinding.FragmentProfileBinding
    private val viewModel:ViewModelProfile by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=com.yapi.databinding.FragmentProfileBinding.inflate(LayoutInflater.from(requireActivity()))
        binding.model=viewModel
        return  binding.root
    }


}