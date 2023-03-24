package com.yapi.views.add_people_email_confirmation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yapi.R
import com.yapi.databinding.FragmentAddPeopleBinding
import com.yapi.databinding.FragmentAddPeopleEmailConfirmationBinding


class AddPeopleEmailConfirmationFragment : Fragment() {
    private lateinit var binding:FragmentAddPeopleEmailConfirmationBinding
    private val viewModel:ViewModelAddPeopleEmailConfirmation by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding=FragmentAddPeopleEmailConfirmationBinding.inflate(LayoutInflater.from(requireContext()))
        binding.model=viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setList()
        binding.apply {

        }
    }

    private fun setList() {
        val list=arguments?.getStringArrayList("personList")
        var listCount="0"
        if(list?.isNotEmpty()==true){
           listCount= list.size.toString()
            binding.rvEmailConfirmationOfAddPeopleEmailConf.adapter=AdapterEmailConfirmation(requireContext(),list)
        }else
        {
            listCount= "0"
        }
        var countValue=requireActivity().getString(R.string.you_have_invited)+" "+listCount+" "+requireActivity().getString(R.string.one_person)
        viewModel.invitedPersonCount.set(countValue)
    }
}