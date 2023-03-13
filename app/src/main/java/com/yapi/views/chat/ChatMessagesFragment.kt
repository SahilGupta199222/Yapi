package com.yapi.views.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yapi.databinding.ChatMessageFragmentLayoutBinding

class ChatMessagesFragment : Fragment() {

    private lateinit var dataBinding: ChatMessageFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding =
            ChatMessageFragmentLayoutBinding.inflate(LayoutInflater.from(requireActivity()))
        initUI()
        return dataBinding.root
    }

    private fun initUI() {


    }
}