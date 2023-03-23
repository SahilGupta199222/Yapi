package com.yapi.views.chat

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yapi.databinding.ChatMessageFragmentLayoutBinding

class ChatMessagesFragment : Fragment() {

    private lateinit var rvChatAdapter: RVchatAdapter
    private lateinit var dataBinding: ChatMessageFragmentLayoutBinding
    val viewModel: ChatViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dataBinding =
            ChatMessageFragmentLayoutBinding.inflate(LayoutInflater.from(requireActivity()))
        dataBinding.mViewModel = viewModel
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        viewModel.screenWidth = width

        viewModel.userType = requireArguments().getString("userType")
        initUI()
        return dataBinding.root
    }

    private fun initUI() {
        rvChatAdapter = RVchatAdapter(requireActivity())
        dataBinding.rvChatList.layoutManager = LinearLayoutManager(requireActivity())
        dataBinding.rvChatList.adapter = rvChatAdapter
    }
}