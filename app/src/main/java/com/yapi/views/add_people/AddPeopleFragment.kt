package com.yapi.views.add_people

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yapi.R
import com.yapi.databinding.FragmentAddPeopleBinding

class AddPeopleFragment : Fragment() {
    private lateinit var binding:FragmentAddPeopleBinding
    private val viewModel:ViewModelAddPeople by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding=FragmentAddPeopleBinding.inflate(LayoutInflater.from(requireActivity()))
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            vModel=viewModel
        }
    }

}