package com.yapi.views.chat

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private var lastVisible: Int?=-1
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

        if (checkDeviceType()) {
            viewModel.backButtonVisible.set(false)

            if(viewModel.userType==Constants.CUSTOMERS_KEY || viewModel.userType==Constants.CONVERSATIONS_KEY)
            {
                viewModel.groupImageVisible.set(true)
                viewModel.groupIconVisible.set(false)
                viewModel.liveUserVisible.set(true)
                viewModel.noImageOnlyNameVisible.set(true)
                viewModel.groupAllPhotos.set(false)
            }else
            {
                viewModel.groupImageVisible.set(true)
                viewModel.groupIconVisible.set(true)
                viewModel.liveUserVisible.set(false)
                viewModel.noImageOnlyNameVisible.set(false)
                viewModel.groupAllPhotos.set(true)
            }
        } else {
            viewModel.backButtonVisible.set(true)
            viewModel.groupImageVisible.set(false)
            viewModel.groupIconVisible.set(false)
            viewModel.liveUserVisible.set(false)
            viewModel.groupAllPhotos.set(false)
        }

        initUI()
        return dataBinding.root
    }

    //For UI Intialization
    private fun initUI() {

        var arraylist=ArrayList<String>()
        arraylist.clear()
        arraylist.add("AA")
        arraylist.add("AA")
        arraylist.add("AA")
        arraylist.add("AA")
        arraylist.add("AA")
        arraylist.add("AA")
        arraylist.add("AA")
        arraylist.add("AA")
        arraylist.add("AA")
        arraylist.add("AA")
        arraylist.add("AA")
        arraylist.add("AA")

        rvChatAdapter = RVchatAdapter(requireActivity(), this,arraylist)
        dataBinding.rvChatList.layoutManager = LinearLayoutManager(requireActivity())
        dataBinding.rvChatList.adapter = rvChatAdapter
        hideKeyboardObserverMethod()
        scrollRecylerViewMethod()

    }

    private fun scrollRecylerViewMethod() {

        dataBinding.rvChatList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                val totalItemCount = layoutManager.itemCount
                 lastVisible = layoutManager.findLastVisibleItemPosition()
                val endHasBeenReached = lastVisible!! + 5 >= totalItemCount
                if (totalItemCount > 0 && endHasBeenReached) {
                    //you have reached to the bottom of your recycler view
                }
            }
        })
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

      //  if(rvChatAdapter)

       //  popUp.showAtLocation(mView, Gravity.RIGHT,0,0);
        popUp.isTouchable = true
        popUp.isFocusable = true
        popUp.isOutsideTouchable = true

        //popUp.setIsLaidOutInScreen(true)
     //   val btnViewProfile =

        popUp.showAsDropDown(ivMoreImageView)

        popUp.setOutsideTouchable(true)

        popUp.setTouchInterceptor(object : View.OnTouchListener {
          override fun onTouch(v: View?, event: MotionEvent): Boolean {
                if (event.getAction() === MotionEvent.ACTION_OUTSIDE) {
                    popUp.dismiss()
                    return true
                }
                return false
            }
        })

        var editMessageList = ArrayList<EditMessageData>()
        editMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.reply_message_text),
            R.drawable.reply_message_icon))
        editMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.edit_message_text),
            R.drawable.edit_message_icon))
        editMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.save_message_text),
            R.drawable.save_message))
        editMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.mark_unread_text),
            R.drawable.mark_as_unread))
        editMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.copy_message_text),
            R.drawable.copy_message_icon))
        editMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.pin_conversation_text),
            R.drawable.push_pin))
        editMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.delete_message_text),
            R.drawable.delete_chat_icon))

        var rvEditMessageAdapter = RVEditMessageAdapter(requireActivity(), editMessageList,
            object : ClickMessage {
                override fun onClickListener(position: Int) {
                //For click on Edit Message Options
                    popUp.dismiss()
                }
            })
        var rvEditMessages = mView.findViewById<RecyclerView>(R.id.rvEditMessages)
        rvEditMessages.layoutManager = LinearLayoutManager(requireActivity())
        rvEditMessages.adapter = rvEditMessageAdapter
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