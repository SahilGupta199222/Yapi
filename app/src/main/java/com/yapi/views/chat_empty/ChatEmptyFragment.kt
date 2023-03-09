package com.yapi.views.chat_empty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yapi.R
import com.yapi.databinding.FragmentChatEmptyBinding

class ChatEmptyFragment : Fragment() {
    private lateinit var binding:FragmentChatEmptyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentChatEmptyBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        binding.apply {
            imgTempDrawableChatEmpty.setOnClickListener {
                findNavController().navigate(R.id.action_chatEmptyFragment_to_menuFragment)
            }
            imgDemoIconEmptyChat.setOnClickListener {
                findNavController().navigate(R.id.action_chatEmptyFragment_to_menuFragment)
            }
            txtTempChatEmpty.setOnClickListener {
                findNavController().navigate(R.id.action_chatEmptyFragment_to_menuFragment)
            }
        }
    }
}