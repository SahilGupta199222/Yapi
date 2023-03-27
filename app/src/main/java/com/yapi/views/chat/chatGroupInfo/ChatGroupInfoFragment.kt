package com.yapi.views.chat.chatGroupInfo

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yapi.databinding.ChatGroupInfoFragmentBinding
import com.yapi.databinding.ChatUserInfoFragmentBinding
import com.yapi.views.chat.chatUserInfo.ChatUserInfoViewModel
import com.yapi.views.chat.chatUserInfo.RVLinksAdapter
import com.yapi.views.chat.chatUserInfo.RVPhotoMediaAdapter

class ChatGroupInfoFragment():Fragment() {
    private lateinit var rvLinkAdapter: RVLinksAdapter
    private lateinit var mediaAdapter: RVPhotoMediaAdapter
    private lateinit var dataBinding: ChatGroupInfoFragmentBinding
    private var screenWidth: Int?=0
    val viewModel: ChatGroupInfoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = ChatGroupInfoFragmentBinding.inflate(inflater)
        dataBinding.vModel=viewModel
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels

        initUI()
        return dataBinding.root
    }

    private fun initUI() {
        setPhotoAdapterMethod()
        setLinkAdapterMethod()
        viewModel.setAdapterData()
        viewModel.setTemplateAdapterData()
    }

    fun setPhotoAdapterMethod()
    {
        var finalPerPhoto=screenWidth!!.toFloat()/3.58f
        mediaAdapter= RVPhotoMediaAdapter(requireActivity(),finalPerPhoto!!.toInt())
        dataBinding.rvMediaView.layoutManager= GridLayoutManager(requireActivity(),3)
        dataBinding.rvMediaView.adapter=mediaAdapter
    }

    fun setLinkAdapterMethod()
    {
        rvLinkAdapter= RVLinksAdapter(requireActivity())
        dataBinding.rvLinksView.layoutManager= LinearLayoutManager(requireActivity())
        dataBinding.rvLinksView.adapter=rvLinkAdapter
    }
}