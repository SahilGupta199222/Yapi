package com.yapi.views.chat.chatUserInfo

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.lifecycle.ViewModel
import com.yapi.R
import com.yapi.recycleradapter.RecyclerAdapter
import dagger.hilt.android.lifecycle.HiltViewModel


class ChatUserInfoViewModel():ViewModel() {
    init {

    }
    val rvFilesAdapter by lazy { RecyclerAdapter<TempModel>(R.layout.rv_file_adapter_layout) }
    val rvTemplatesAdapter by lazy { RecyclerAdapter<TempModel>(R.layout.rvtemplate_layout) }

    var userType=ObservableField("")
    var userInformation=ObservableBoolean(false)

    fun setAdapterData()
    {
        var fileList=ArrayList<TempModel>()
        fileList.add(TempModel("AAA"))
        fileList.add(TempModel("AAA"))
        fileList.add(TempModel("AAA"))
        rvFilesAdapter.addItems(fileList)
        rvFilesAdapter.notifyDataSetChanged()
    }

    fun setTemplateAdapterData()
    {
        var fileList=ArrayList<TempModel>()
        fileList.add(TempModel("AAA"))
        fileList.add(TempModel("AAA"))
        fileList.add(TempModel("AAA"))
        rvTemplatesAdapter.addItems(fileList)
        rvTemplatesAdapter.notifyDataSetChanged()
    }
}