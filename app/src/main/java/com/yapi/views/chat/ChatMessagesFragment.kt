package com.yapi.views.chat

import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.Constants
import com.yapi.common.GroupEvent
import com.yapi.common.checkDeviceType
import com.yapi.common.hideKeyboard
import com.yapi.databinding.ChatMessageFragmentLayoutBinding
import com.yapi.views.create_group.CreateGroupFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ChatMessagesFragment : Fragment(), MessageClickListener {

    /*  companion object {
          fun newInstanceChatMethod(title: String): ChatMessagesFragment {
              val args = Bundle()
              args.putString("11", title)
              val fragment = ChatMessagesFragment()
              fragment.arguments = args
              return fragment
          }
      }*/

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
        val height = displayMetrics.heightPixels
        viewModel.screenWidth = width
        viewModel.screenHeight = height

        viewModel.userType = requireArguments().getString("userType")

        if(checkDeviceType())
        {
            viewModel.backButtonVisible.set(false)
        }else
        {
            viewModel.backButtonVisible.set(true)
        }

        initUI()
        return dataBinding.root
    }

    //For UI Intialization
    private fun initUI() {
        rvChatAdapter = RVchatAdapter(requireActivity(), this)
        dataBinding.rvChatList.layoutManager = LinearLayoutManager(requireActivity())
        dataBinding.rvChatList.adapter = rvChatAdapter
        hideKeyboardObserverMethod()
    }

    //When click on the three dots
    fun showEditMessageMethod(ivMoreImageView: ImageView) {
        val mView: View = LayoutInflater.from(MainActivity.activity!!.get())
            .inflate(com.yapi.R.layout.edit_chat_message_layout, null, false)
        var newWidth = viewModel.screenWidth!! / 1.5

        //   val popUp = PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false)
        val popUp = PopupWindow(mView,
            newWidth.toInt(),
            LinearLayout.LayoutParams.WRAP_CONTENT,
            false)
        // popUp.showAtLocation(mView, Gravity.RIGHT,0,0);
        popUp.isTouchable = true
        popUp.isFocusable = true
        popUp.isOutsideTouchable = true
        val btnViewProfile =

            popUp.showAsDropDown(ivMoreImageView)
        var constraintsEditMessage =
            mView.findViewById<ConstraintLayout>(R.id.constraintsEditMessage)
        constraintsEditMessage.setOnClickListener {
            popUp.dismiss()
        }
        var constraintsCopyMessage =
            mView.findViewById<ConstraintLayout>(R.id.constraintsCopyMessage)
        constraintsCopyMessage.setOnClickListener {
            popUp.dismiss()
        }
        var constraintsDeleteMessage =
            mView.findViewById<ConstraintLayout>(R.id.constraintsDeleteMessage)
        constraintsDeleteMessage.setOnClickListener {
            popUp.dismiss()
        }
    }

    override fun onMesssageListener(position: Int, ivMoreImageView: ImageView) {
        showEditMessageMethod(ivMoreImageView)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: GroupEvent?) {
        // Do something
        Log.e("gsegegsgsgs===", System.currentTimeMillis().toString())

        if (event!!.screenName == Constants.CREATEGOUP_KEY) {
            CreateGroupFragment.newInstanceCreateGroup("")
                .showNow(requireActivity().supportFragmentManager, " SimpleDialog.TAG")
        }
    }

    fun hideKeyboardObserverMethod() {
        viewModel.keyboardHideData.observe(requireActivity(), Observer {
            var data = it as Boolean
            if (data) {
                requireActivity().hideKeyboard()
            }
        })
    }
}