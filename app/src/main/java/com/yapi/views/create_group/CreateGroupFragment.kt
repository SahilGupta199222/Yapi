package com.yapi.views.create_group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.yapi.R
import com.yapi.databinding.FragmentCreateGroupBinding
import com.yapi.views.menu_screen.MenuViewModel


class CreateGroupFragment : Fragment() {
    private lateinit var binding: FragmentCreateGroupBinding
    val viewModel: ViewModelCreateGroup by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateGroupBinding.inflate(LayoutInflater.from(requireActivity()))
        binding.vModel = viewModel
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