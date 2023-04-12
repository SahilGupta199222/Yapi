package com.yapi.views.chat

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.masoudss.lib.SeekBarOnProgressChanged
import com.masoudss.lib.WaveformSeekBar
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.Constants
import com.yapi.common.GroupEvent
import com.yapi.common.checkDeviceType
import com.yapi.common.hideKeyboard
import com.yapi.databinding.ChatMessageFragmentLayoutBinding
import com.yapi.pref.PreferenceFile
import com.yapi.views.create_group.CreateGroupFragment
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
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

    private var lastVisible: Int? = -1
    private lateinit var rvChatAdapter: RVchatAdapter
    private lateinit var dataBinding: ChatMessageFragmentLayoutBinding

    private val TAG = "asdifjhasjkdfnskandf"
    private var mRecorder: MediaRecorder? = null
    private var mPlayer: MediaPlayer? = null
    private var recordFile: File? = null
    private var playRecodingStatus = true
    private var recoderPlayTime = 0
    private var recoderTimeStemp = 0
    private var recoderPlayLiveTime = 0
    private var mHandler: Handler? = null
    private var runnableGetTimeRecoderPlay: Runnable? = null
    private var runnableGetTimeRecoderLive: Runnable? = null
    val viewModel: ChatViewModel by viewModels()

    @Inject
    lateinit var preferenceFile:PreferenceFile
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

     //   var second_frame_height= preferenceFile.fetchStringValue("second_frame_height").toInt()
        var second_frame_width=  preferenceFile.fetchStringValue("second_frame_width").toInt()
        viewModel.SECOND_FRAME_WIDTH=second_frame_width

        viewModel.userType = requireArguments().getString("userType")

        if (checkDeviceType()) {
            viewModel.backButtonVisible.set(false)

            if (viewModel.userType == Constants.CUSTOMERS_KEY || viewModel.userType == Constants.CONVERSATIONS_KEY) {
                viewModel.groupImageVisible.set(true)
                viewModel.groupIconVisible.set(false)
                viewModel.liveUserVisible.set(true)
                viewModel.noImageOnlyNameVisible.set(true)
                viewModel.groupAllPhotos.set(false)
            } else {
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
        dataBinding.apply {
            val arraylist = ArrayList<String>()
            arraylist.clear()
            arraylist.add("AA")
            arraylist.add("BB")
            arraylist.add("CC")
            arraylist.add("DD")
            arraylist.add("EE")
            arraylist.add("FF")
            arraylist.add("AA")
            arraylist.add("AA")
            arraylist.add("AA")
            arraylist.add("AA")
            arraylist.add("AA")
            arraylist.add("AA")

            rvChatAdapter = RVchatAdapter(requireActivity(), this@ChatMessagesFragment, arraylist)
            dataBinding.rvChatList.layoutManager = LinearLayoutManager(requireActivity())
            dataBinding.rvChatList.adapter = rvChatAdapter
            hideKeyboardObserverMethod()
            scrollRecylerViewMethod()
            clickListner()
            runnableGetTimeRecoderPlay = object : Runnable {
                @SuppressLint("SetTextI18n")
                override fun run() {
                    try {
                        if (recoderTimeStemp == 10) {
                            recoderTimeStemp = 0
                            recoderPlayTime += 1

                        } else {
                            Log.i(TAG, "total duration is ${mPlayer?.duration}")
                            if (recoderTimeStemp < (mPlayer?.duration?.div(1000)
                                    ?: (30620 / 1000))
                            ) {
                                recoderTimeStemp += 1
                            }
//                            recoderTimeStemp += 1
                        }

                        if (recoderPlayTime.toString()
                                .trim().length < 2
                        ) txtAudioTimeRecodingPlayChat.text = "00:0$recoderPlayTime"
                        else txtAudioTimeRecodingPlayChat.text = "00:$recoderPlayTime"
                        val currentPosition: Int = mPlayer?.currentPosition ?: 0
                        val totalDuration: Int = mPlayer?.duration ?: 0
                        val positionPercent =
                            currentPosition.toFloat() / totalDuration.toFloat() * 100
                        seekBarRecodingPlayChat.progress = positionPercent
                    } finally {
                        mHandler!!.postDelayed(this, 100)
                    }
                }
            }
        }
    }

    private fun clickListner() {
        dataBinding.apply {
            imgMicIconChatDemo.setOnClickListener {

                if (runnableGetTimeRecoderLive != null) {
                    mHandler?.removeCallbacks(runnableGetTimeRecoderLive!!)
                    runnableGetTimeRecoderLive = null
                    mRecorder?.stop()
                    mRecorder?.release()
                    mRecorder = null
                    recordFile = null
                }
                if (runnableGetTimeRecoderPlay != null) {
                    mHandler?.removeCallbacks(runnableGetTimeRecoderPlay!!)
                }
                if (mHandler != null) {
                    mHandler = null
                }
                if (mPlayer != null) {
                    mPlayer?.release()
                    mPlayer = null
                }












                imgMicIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),
                    R.color.blueColor))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    gettingMicPermission(requireActivity(),
                        arrayListOf(Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.READ_MEDIA_AUDIO))
                } else {
                    gettingMicPermission(requireActivity(),
                        arrayListOf(Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE))

                }



                Handler(Looper.myLooper()!!).postDelayed(object : Runnable {
                    override fun run() {
                        imgMicIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),
                            R.color.medium_grey_color))
                    }
                }, 250)
            }
        }
    }

    private fun scrollRecylerViewMethod() {

        dataBinding.rvChatList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                val totalItemCount = layoutManager!!.itemCount
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
            .inflate(R.layout.edit_chat_message_layout, null, false)
        val newWidth = viewModel.screenWidth!! / 1.5

        //   val popUp = PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false)
        val popUp =
            PopupWindow(mView, newWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT, false)

        //  if(rvChatAdapter)

        //  popUp.showAtLocation(mView, Gravity.RIGHT,0,0);
        popUp.isTouchable = true
        popUp.isFocusable = true
        popUp.isOutsideTouchable = true

        //popUp.setIsLaidOutInScreen(true)
        //   val btnViewProfile =

        popUp.showAsDropDown(ivMoreImageView)

        popUp.isOutsideTouchable = true

        popUp.setTouchInterceptor(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                if (event.action === MotionEvent.ACTION_OUTSIDE) {
                    popUp.dismiss()
                    return true
                }
                return false
            }
        })

        val editMessageList = ArrayList<EditMessageData>()
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

        val rvEditMessageAdapter =
            RVEditMessageAdapter(requireActivity(), editMessageList, object : ClickMessage {
                override fun onClickListener(position: Int) {
                    //For click on Edit Message Options
                    popUp.dismiss()
                }
            })
        val rvEditMessages = mView.findViewById<RecyclerView>(R.id.rvEditMessages)
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
            val data = it as Boolean
            if (data) {
                requireActivity().hideKeyboard()
            }
        })
    }

    private fun gettingMicPermission(context: Activity, permission: java.util.ArrayList<String>) {
        Dexter.withContext(context).withPermissions(permission)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    if (p0?.areAllPermissionsGranted() == true) {
//                    Toast.makeText(requireContext(), "All the permissions are granted..", Toast.LENGTH_SHORT).show();
                        recodeAudio()
                        Log.i(TAG, "All the permissions are granted..")
                    } else {
                        if (p0?.isAnyPermissionPermanentlyDenied == true) {
                            Log.i(TAG, "Permission denied")
//                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.i(TAG, "Permission denied temp")
                            gettingMicPermission(context, permission)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?,
                ) {
                    p1?.continuePermissionRequest()
                }
            }).withErrorListener { error ->
                Toast.makeText(requireContext(),
                    "Error occurred! ${error.name}",
                    Toast.LENGTH_SHORT)
                    .show()
            }.onSameThread().check()
    }

    fun mediaRecorderReady() {
        mRecorder = MediaRecorder()
        mRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        mRecorder?.setOutputFile(recordFile?.absolutePath)
    }

    fun recodeAudio() {
        dataBinding.apply {
            mHandler = Handler(Looper.myLooper()!!)
            recoderTimeStemp = 0
            runnableGetTimeRecoderLive = object : Runnable {
                override fun run() {
                    try {
                        if (recoderTimeStemp == 10) {
                            recoderPlayLiveTime += 1
                            recoderTimeStemp = 0
                        } else {
                            recoderTimeStemp += 1
                        }
                        if (recoderPlayLiveTime.toString()
                                .trim().length < 2
                        ) txtAudioTimeRecodingLiveChat.text = "00:0$recoderPlayLiveTime"
                        else txtAudioTimeRecodingLiveChat.text = "00:$recoderPlayLiveTime"
                        val amplitude = mRecorder?.maxAmplitude
                        dataBinding.audioRecodingViewRecodingLiveChat.update(amplitude ?: 0)
                    } finally {
                        mHandler!!.postDelayed(this, 100)
                    }
                }
            }
            layoutRecodingChat.visibility = View.VISIBLE
            etRichChatDemo.visibility = View.GONE
            layoutRecodingLiveChat.visibility = View.VISIBLE
            layoutRecodingPlayChat.visibility = View.GONE
            txtAudioTimeRecodingLiveChat.text = ""
            audioRecodingViewRecodingLiveChat.recreate()
            recoderPlayLiveTime = 0
            recoderPlayTime = 0
            recordFile = File(requireContext().filesDir, UUID.randomUUID().toString() + ".mp3")
            if (!recordFile?.exists()!!) {
                recordFile?.createNewFile()
            }
            mediaRecorderReady()
            try {
                mRecorder?.prepare()
                runnableGetTimeRecoderLive?.run()
                mRecorder?.start()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i(TAG, "IoException ${e.message}")
            }

            imgDeleteRecodingChat.setOnClickListener {
                if (runnableGetTimeRecoderLive != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mRecorder?.pause()
                    }
                    mHandler?.removeCallbacks(runnableGetTimeRecoderLive!!)
                    runnableGetTimeRecoderLive = null
                    mRecorder?.stop()
                    mRecorder?.release()
                    mRecorder = null
                    recordFile = null
                    layoutRecodingChat.visibility = View.GONE
                    etRichChatDemo.visibility = View.VISIBLE
                } else {
                    recordFile = null
                    layoutRecodingChat.visibility = View.GONE
                    etRichChatDemo.visibility = View.VISIBLE
                }
                if (runnableGetTimeRecoderPlay != null) {
                    mHandler?.removeCallbacks(runnableGetTimeRecoderPlay!!)
                }
                if (mHandler != null) {
                    mHandler = null
                }
                if (mPlayer != null) {
                    mPlayer?.release()
                    mPlayer = null
                }
            }

            imgDoneRecodingLiveChat.setOnClickListener {
                if (runnableGetTimeRecoderLive != null) {
                    mHandler?.removeCallbacks(runnableGetTimeRecoderLive!!)
                    runnableGetTimeRecoderLive = null
//                    mHandlerLive=null
                    mRecorder?.stop()
                    mRecorder?.release()
                    mRecorder = null
                }
                seekBarRecodingPlayChat.setSampleFrom(recordFile?.absolutePath.toString())
                layoutRecodingLiveChat.visibility = View.GONE
                layoutRecodingPlayChat.visibility = View.VISIBLE
                txtAudioTimeRecodingPlayChat.text = "00:00"
                imgRecodingPlayBtn.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.ic_baseline_play_arrow_24))
                seekBarRecodingPlayChat.onProgressChanged = object : SeekBarOnProgressChanged {
                    override fun onProgressChanged(
                        waveformSeekBar: WaveformSeekBar,
                        progress: Float,
                        fromUser: Boolean,
                    ) {
                        if (fromUser) {
                            Log.i(TAG,
                                "onProgress change  $waveformSeekBar\nProgress $progress\nFromUser$fromUser")
/*
                            mPlayer?.seekTo(progress.toInt() * 100)
                            seekBarRecodingPlayChat.progress = progress * 100
                            if(playRecodingStatus) {
                                mHandler?.removeCallbacks(runnableGetTimeRecoderPlay!!)
                                recoderPlayTime = progress.toInt() / 10
                                Log.i(TAG, "change progress is ${progress.toInt()}")
                                runnableGetTimeRecoderPlay?.run()
                            }*/

                            if (progress.toInt() > 0) {
                                Log.i("asfdanbs", "progress greater then 0{}")
                                mPlayer?.seekTo(progress.toInt() * 100)
                                val currentPosition: Int = mPlayer?.currentPosition ?: 0
                                val totalDuration: Int = mPlayer?.duration ?: 0
                                val positionPercent =
                                    currentPosition.toFloat() / totalDuration.toFloat() * 100
                                seekBarRecodingPlayChat.progress = positionPercent
                                recoderPlayTime = progress.toInt() / 10
                                recoderTimeStemp = progress.toInt() / 10
                                if (!playRecodingStatus) {
                                    Log.i("asfdanbs", "progress greater then then 0 running")
                                    mHandler?.removeCallbacks(runnableGetTimeRecoderPlay!!)
                                    runnableGetTimeRecoderPlay?.run()
                                } else {
                                    Log.i("asfdanbs", "progress greater then then 0 stop")

                                    mHandler?.removeCallbacks(runnableGetTimeRecoderPlay!!)
                                    txtAudioTimeRecodingPlayChat.text = "00:0$recoderPlayTime"
                                }
                            } else {
                                Log.i("asfdanbs", "progress less then then 0")
                                mPlayer?.seekTo(0)
                                seekBarRecodingPlayChat.progress = 0.0F
                                recoderPlayTime = 0
                                recoderTimeStemp = 0
                                if (!playRecodingStatus) {
                                    Log.i("asfdanbs", "progress less then then 0 running")
                                    mHandler?.removeCallbacks(runnableGetTimeRecoderPlay!!)
                                    runnableGetTimeRecoderPlay?.run()
                                } else {
                                    Log.i("asfdanbs", "progress less then then 0 stop")
                                    txtAudioTimeRecodingPlayChat.text = "00:0$recoderPlayTime"
                                }
                            }
                        }
                    }
                }

            }
            layoutBtnRecodingPlayChat.setOnClickListener {
                playRecodingStatus = !playRecodingStatus
                if (playRecodingStatus) {
                    imgRecodingPlayBtn.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.ic_baseline_play_arrow_24))
                    pauseAudio()
                } else {
                    imgRecodingPlayBtn.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.ic_baseline_pause_24))
                    playAudio()
                }
            }

        }
    }

    private fun playAudio() {
        if (mPlayer == null && recordFile != null) {
            mPlayer = MediaPlayer()
            mPlayer?.setDataSource(recordFile?.absolutePath.toString())
            Log.i(TAG, "reco ${recordFile?.absolutePath.toString()}")
            mPlayer?.prepare()
            mPlayer?.start()
            recoderTimeStemp = 0
            runnableGetTimeRecoderPlay?.run()
        } else {
            mPlayer?.start()
            runnableGetTimeRecoderPlay?.run()
        }

        mPlayer?.setOnCompletionListener {
            Log.i(TAG, "onComplete called")
            playRecodingStatus = !playRecodingStatus
            dataBinding.imgRecodingPlayBtn.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                R.drawable.ic_baseline_play_arrow_24))
            mHandler?.removeCallbacks(runnableGetTimeRecoderPlay!!)
            recoderPlayTime = 0
            recoderTimeStemp = 0
        }

    }

    private fun pauseAudio() {
        if (mPlayer != null && recordFile != null) {
            mHandler?.removeCallbacks(runnableGetTimeRecoderPlay!!)
            mPlayer?.pause()
        } else {
            Toast.makeText(requireContext(), "Something error", Toast.LENGTH_SHORT).show()
        }
    }


}