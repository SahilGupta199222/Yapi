package com.yapi.views.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yapi.R
import com.yapi.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding:com.yapi.databinding.FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=com.yapi.databinding.FragmentProfileBinding.inflate(LayoutInflater.from(requireActivity()))
        return  binding.root
    }

}