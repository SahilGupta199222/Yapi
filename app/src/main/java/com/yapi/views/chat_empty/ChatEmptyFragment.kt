package com.yapi.views.chat_empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.yapi.R
import com.yapi.databinding.FragmentChatEmptyBinding

class ChatEmptyFragment : Fragment() {
    private lateinit var binding: FragmentChatEmptyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChatEmptyBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            imgTempDrawableChatEmpty.setOnClickListener {
                if (findNavController().currentDestination?.id == R.id.chatEmptyFragment) {
                    findNavController().navigate(R.id.action_chatEmptyFragment_to_menuFragment)
                }
            }
                imgDemoIconEmptyChat.setOnClickListener {
                    if (findNavController().currentDestination?.id == R.id.chatEmptyFragment) {

                        findNavController().navigate(R.id.action_chatEmptyFragment_to_menuFragment)
                    }
                }
                txtTempChatEmpty.setOnClickListener {
                    if (findNavController().currentDestination?.id == R.id.chatEmptyFragment) {

                        findNavController().navigate(R.id.action_chatEmptyFragment_to_menuFragment)
                    }

                }

        }
    }
}