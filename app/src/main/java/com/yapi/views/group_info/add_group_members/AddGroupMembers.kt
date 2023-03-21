package com.yapi.views.group_info.add_group_members

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yapi.R
import com.yapi.databinding.FragmentAddGroupMembersBinding


class AddGroupMembers : Fragment() {
    private lateinit var binding:FragmentAddGroupMembersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentAddGroupMembersBinding.inflate(LayoutInflater.from(requireContext()))
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list=ArrayList<PojoGroupMembers>()
        for(i in 0 until 10){
            list.add(PojoGroupMembers("Name${i+1}","Sales ${i+4}"))
        }
        binding.apply {
            rvGroupMembers.adapter=AdapterGroupMembers(requireContext(),list)
        }
    }


}