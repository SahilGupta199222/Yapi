package com.yapi.views.chat.chatGroupInfo

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.R
import com.yapi.recycleradapter.RecyclerAdapter
import com.yapi.views.chat.chatUserInfo.TempModel

class ChatGroupInfoViewModel:ViewModel() {
    init {

    }

    val rvFilesAdapter by lazy { RecyclerAdapter<TempModel>(R.layout.rv_file_adapter_layout) }
    val rvTemplatesAdapter by lazy { RecyclerAdapter<TempModel>(R.layout.rvtemplate_layout) }

    var userType= ObservableField("")
    var userInformation=ObservableBoolean(false)

    fun onClick(view:View)
    {
        when(view.id)
        {
            R.id.ivBackGroupInfo->{
                view.findNavController().navigateUp()
            }
        }
    }

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