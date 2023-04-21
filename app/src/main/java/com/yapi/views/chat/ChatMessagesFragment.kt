package com.yapi.views.chat

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
<<<<<<< HEAD
=======
import android.text.Html
import android.text.Spannable
>>>>>>> origin/master
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
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
<<<<<<< HEAD
import com.yapi.views.menu_screen.GroupData
import dagger.hilt.android.AndroidEntryPoint
=======
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.richeditor.RichEditor
>>>>>>> origin/master
import jp.wasabeef.richeditor.RichEditor.OnTextChangeListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.util.*
import javax.inject.Inject

<<<<<<< HEAD
@AndroidEntryPoint
class ChatMessagesFragment : Fragment(), MessageClickListener {

    private var styleArrayList: ArrayList<OptionSelectionData>? = null
=======

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

    private var styleArrayList: ArrayList<String>?=null
>>>>>>> origin/master
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
<<<<<<< HEAD
    private var boldClickStatus = false
    private var italicClickStatus = false
    private var underlineClickStatus = false
    private var strikeClickStatus = false
    private var numberParagraphClickStatus = false
    private var bulletParagraphClickStatus = false
    private var leftAlignClickStatus = false
    private var centerAlignClickStatus = false
    private var rightAlignClickStatus = false
=======
    private var boldClickStatus=false
    private var italicClickStatus=false
    private var underlineClickStatus=false
    private var strikeClickStatus=false
    private var numberParagraphClickStatus=false
    private var bulletParagraphClickStatus=false
    private var leftAlignClickStatus=false
    private var centerAlignClickStatus=false
    private var rightAlignClickStatus=false
>>>>>>> origin/master

    val viewModel: ChatViewModel by viewModels()

    @Inject
<<<<<<< HEAD
    lateinit var preferenceFile: PreferenceFile
=======
    lateinit var preferenceFile:PreferenceFile
>>>>>>> origin/master
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

<<<<<<< HEAD
        //   var second_frame_height= preferenceFile.fetchStringValue("second_frame_height").toInt()
        var second_frame_width = preferenceFile.fetchStringValue("second_frame_width").toInt()
        viewModel.SECOND_FRAME_WIDTH = second_frame_width
=======
     //   var second_frame_height= preferenceFile.fetchStringValue("second_frame_height").toInt()
        var second_frame_width=  preferenceFile.fetchStringValue("second_frame_width").toInt()
        viewModel.SECOND_FRAME_WIDTH=second_frame_width
>>>>>>> origin/master

        viewModel.userType = requireArguments().getString("userType")
        dataBinding.createGroupUI.mViewModel=viewModel
        if (requireArguments().getSerializable("group_data") != null && Constants.GROUPS_KEY == viewModel.userType) {
            var group_data = requireArguments().getSerializable("group_data") as GroupData
            if (group_data != null) {
                viewModel.titleName.set(group_data.name)

                if(group_data.invitaions.size>0)
                {
                   var list2= group_data.invitaions.filter { it.status.toLowerCase()!="pending" }
                if ((list2.size + 1) > 1) {
                    viewModel.memberValue.set((list2.size + 1).toString() + " Members")
                } else {
                    viewModel.memberValue.set((list2.size + 1).toString() + " Member")
                }
            }else
                {
                    viewModel.memberValue.set("1 Member")
                }
                dataBinding.createGroupUI.groupScreenInfoEmpty!!.visibility=View.VISIBLE
                dataBinding.rvChatList.visibility=View.GONE
                dataBinding.chatListconstraints.visibility=View.GONE

                //group_data.invitaions
                if(group_data.image_url!="")
                {
                    dataBinding.ivGroupImage.visibility=View.VISIBLE
                    dataBinding.relNameValue.visibility=View.GONE
                 //   viewModel.noImageOnlyNameVisible.set(false)
                  //  viewModel.groupImageVisible.set(true)
                }else
                {
               //     viewModel.noImageOnlyNameVisible.set(true)
                    dataBinding.tvGroupImageText.setText(group_data.name.substring(0,1).toUpperCase())
                    dataBinding.ivGroupImage.visibility=View.GONE
                    dataBinding.relNameValue.visibility=View.VISIBLE
                  //  viewModel.groupImageVisible.set(false)
                }

                if(checkDeviceType())
                {
                    if(group_data.image_url!="")
                    {
                        viewModel.noImageOnlyNameVisible.set(false)
                        viewModel.groupImageVisible.set(true)
                    }else
                    {
                        viewModel.noImageOnlyNameVisible.set(true)
                        viewModel.groupImageVisible.set(false)
                    }
                }else
                {
                    viewModel.noImageOnlyNameVisible.set(false)
                    viewModel.groupImageVisible.set(false)
                }

                if(group_data.user_id==preferenceFile.fetchStringValue(Constants.LOGIN_USER_ID))
                {
                    viewModel.emptyGroupMessageTitle.set(requireActivity().resources.getString(R.string.created_group_title_chat))
                }else
                {
                    viewModel.emptyGroupMessageTitle.set(requireActivity().resources.getString(R.string.created_group_title_chat))

                }
            }
        }else
        {
            viewModel.noImageOnlyNameVisible.set(false)
            viewModel.titleName.set("Group Name")
            viewModel.memberValue.set("5 Members")
            dataBinding.createGroupUI.groupScreenInfoEmpty!!.visibility=View.GONE
            dataBinding.rvChatList.visibility=View.VISIBLE
            dataBinding.chatListconstraints.visibility=View.VISIBLE
            dataBinding.ivGroupImage.visibility=View.VISIBLE
            dataBinding.relNameValue.visibility=View.GONE

            viewModel.noImageOnlyNameVisible.set(false)
            viewModel.groupImageVisible.set(false)
            viewModel.emptyGroupMessageTitle.set(requireActivity().resources.getString(R.string.created_group_title_chat))
        }

        if (checkDeviceType()) {
            viewModel.backButtonVisible.set(false)
            if (viewModel.userType == Constants.CUSTOMERS_KEY || viewModel.userType == Constants.CONVERSATIONS_KEY) {
<<<<<<< HEAD
              //  viewModel.groupImageVisible.set(true)
=======
                viewModel.groupImageVisible.set(true)
>>>>>>> origin/master
                viewModel.groupIconVisible.set(false)
                viewModel.liveUserVisible.set(true)
               // viewModel.noImageOnlyNameVisible.set(true)
                viewModel.groupAllPhotos.set(false)
            } else {
<<<<<<< HEAD
               // viewModel.groupImageVisible.set(true)
=======
                viewModel.groupImageVisible.set(true)
>>>>>>> origin/master
                viewModel.groupIconVisible.set(true)
                viewModel.liveUserVisible.set(false)
              //  viewModel.noImageOnlyNameVisible.set(false)
                viewModel.groupAllPhotos.set(true)
            }
        } else {
            viewModel.backButtonVisible.set(true)
         //   viewModel.groupImageVisible.set(false)
            viewModel.groupIconVisible.set(false)
            viewModel.liveUserVisible.set(false)
            viewModel.groupAllPhotos.set(false)
        }

        initUI()
        return dataBinding.root
    }

<<<<<<< HEAD
    fun setTextListener() {
        dataBinding.etRichChatDemo.setOnTextChangeListener(OnTextChangeListener {
            // Handle text changes here
            var text = it as String
            Log.e("length_of_string===", text.length.toString())
            Log.e("wsmfdmkfmekfmef===", dataBinding.etRichChatDemo.html.toString())

            text = text.replace("<br>", "")

            var newText = ""
            if (text.length > 4) {
                newText = text.substring(text.length - 4, text.length)
            } else {
                newText = text.substring(0, text.length)
            }




            Log.e("wsmfdmkfmekfmef11===", newText)
            //  dataBinding.etNewRichText.text= (Html.fromHtml(newText)).get((Html.fromHtml(newText)).length-1).toString()
            Log.e("wsmfdmkfmekfmef3333===", dataBinding.etNewRichText.text.toString())
            /*  if (appliedStyles.contains(RichEditorType.BOLD)) {
                  // Bold style is currently applied
              }

              if (appliedStyles.contains(RichEditorType.ITALIC)) {
                  // Italic style is currently applied
              }

              if (appliedStyles.contains(RichEditorType.UNDERLINE)) {
                  // Underline style is currently applied
0               }*/
/*
            for(idx in 0 until text.length)
            {
                var newText=""
                if(text.length>4) {
                    newText = text.substring(text.length - 4, text.length)
                    if(newText=="</b>")
                    {
                        updateBoldDataBack()
                    }else
                        if(newText=="</i>")
                        {

                        }
                        else
                            if(newText=="</u>")
                            {

                            }
                            else
                                if(newText=="</strike>")
                                {

                                }
                }else
                {
                    newText = text.substring(0, text.length)
                }
            }*/


            // if(styleArrayList!!.size>0){
            if ((styleArrayList!!.size > 0 && styleArrayList!![styleArrayList!!.size - 1].name == "bold") || newText == "</b>") {
                Log.e("fnefefef===", "Bold_enter")
                if (newText != "</b>") {
                    if (boldClickStatus) {
                        boldClickStatus = false
                        updateBoldText()
                    }
                } else {
                    if (!boldClickStatus) {
                        boldClickStatus = true
                        updateBoldText()
                    }
                }
            } else
                if ((styleArrayList!!.size > 0 && styleArrayList!![styleArrayList!!.size - 1].name == "underline") || newText == "</u>") {
                    if (newText != "</u>") {
                        if (underlineClickStatus) {
                            underlineClickStatus = false
                            ForUnderLineText()
                        }
                    } else {
                        if (!underlineClickStatus) {
                            underlineClickStatus = true
                            ForUnderLineText()
                        }
                    }
                } else if ((styleArrayList!!.size > 0 && styleArrayList!![styleArrayList!!.size - 1].name == "italic") || newText == "</i>") {
                    if (newText != "</i>") {
                        if (italicClickStatus) {
                            italicClickStatus = false
                            setItalicForText()
                        }
                    } else {
                        if (!italicClickStatus) {
                            italicClickStatus = true
                            setItalicForText()
                        }
                    }
                } else if ((styleArrayList!!.size > 0 && styleArrayList!![styleArrayList!!.size - 1].name == "strike") || newText == "ke>") {
                    if (newText != "ke>") {
                        if (strikeClickStatus) {
                            strikeClickStatus = false
                            forStrikeMethod()
                        }
                    } else {
                        if (!strikeClickStatus) {
                            strikeClickStatus = true
                            forStrikeMethod()
                        }
                    }
                }
//
            //italic
            /*}else{
                if(boldClickStatus) {
                    boldClickStatus = false
                    updateBoldText()
                }
                if(underlineClickStatus) {
                    underlineClickStatus = false
                    ForUnderLineText()
                }
            }
*/
            /*if(newText!="/b>")
            {
                if(boldClickStatus) {
                    boldClickStatus = false
                    updateBoldText()
                }
            }else
                if(newText!="/u>")
                {
                    if(underlineClickStatus) {
                        underlineClickStatus = false
                        ForUnderLineText()
                    }
                }else
            {
                if(!boldClickStatus) {
                    boldClickStatus = true
                    updateBoldText()
                }
            }*/

            //  }
=======
    fun setTextListener()
    {
        dataBinding.etRichChatDemo.setOnTextChangeListener(OnTextChangeListener {
            // Handle text changes here
            var text=it as String
            Log.e("length_of_string===",text.length.toString())
            Log.e("wsmfdmkfmekfmef===", dataBinding.etRichChatDemo.html.toString())

          text= text.replace("<br>","")

            if(text.length>4) {
                var newText = text.substring(text.length - 3, text.length)
                Log.e("wsmfdmkfmekfmef11===", newText)
                dataBinding.etNewRichText.text= (Html.fromHtml(newText)).get((Html.fromHtml(newText)).length-1).toString()
                Log.e("wsmfdmkfmekfmef3333===", dataBinding.etNewRichText.text.toString())
              /*  if (appliedStyles.contains(RichEditorType.BOLD)) {
                    // Bold style is currently applied
                }

                if (appliedStyles.contains(RichEditorType.ITALIC)) {
                    // Italic style is currently applied
                }

                if (appliedStyles.contains(RichEditorType.UNDERLINE)) {
                    // Underline style is currently applied
                }*/

                // if(styleArrayList!!.size>0){
                if((styleArrayList!!.size>0 && styleArrayList!![styleArrayList!!.size-1].toString()=="bold") || newText=="/b>")
                {
                    Log.e("fnefefef===","Bold_enter")
                    if(newText!="/b>")
                    {
                        if(boldClickStatus) {
                            boldClickStatus = false
                            updateBoldText()
                        }
                    }else
                    {
                        if(!boldClickStatus) {
                            boldClickStatus = true
                            updateBoldText()
                        }
                    }
                }else
                    if((styleArrayList!!.size>0 && styleArrayList!![styleArrayList!!.size-1].toString()=="underline") || newText=="/u>")
                    {
                        if(newText!="/u>")
                        {
                            if(underlineClickStatus) {
                                underlineClickStatus = false
                                ForUnderLineText()
                            }
                        }else
                        {
                            if(!underlineClickStatus) {
                                underlineClickStatus = true
                                ForUnderLineText()
                            }
                        }
                    }else if((styleArrayList!!.size>0 && styleArrayList!![styleArrayList!!.size-1].toString()=="italic") || newText=="/i>")
                    {
                        if(newText!="/i>")
                        {
                            if(italicClickStatus) {
                                italicClickStatus = false
                                setItalicForText()
                            }
                        }else
                        {
                            if(!italicClickStatus) {
                                italicClickStatus = true
                                setItalicForText()
                            }
                        }
                    }

                    else if((styleArrayList!!.size>0 && styleArrayList!![styleArrayList!!.size-1].toString()=="strike") || newText=="ke>")
                    {
                        if(newText!="ke>")
                        {
                            if(strikeClickStatus) {
                                strikeClickStatus = false
                                forStrikeMethod()
                            }
                        }else
                        {
                            if(!strikeClickStatus) {
                                strikeClickStatus = true
                                forStrikeMethod()
                            }
                        }
                    }
//
                //italic
                /*}else{
                    if(boldClickStatus) {
                        boldClickStatus = false
                        updateBoldText()
                    }
                    if(underlineClickStatus) {
                        underlineClickStatus = false
                        ForUnderLineText()
                    }
                }
*/
                /*if(newText!="/b>")
                {
                    if(boldClickStatus) {
                        boldClickStatus = false
                        updateBoldText()
                    }
                }else
                    if(newText!="/u>")
                    {
                        if(underlineClickStatus) {
                            underlineClickStatus = false
                            ForUnderLineText()
                        }
                    }else
                {
                    if(!boldClickStatus) {
                        boldClickStatus = true
                        updateBoldText()
                    }
                }*/

            }
>>>>>>> origin/master
            //  if(dataBinding.et)
        })
    }

<<<<<<< HEAD
/*    fun updateBoldDataBack( newText:String)
    {
        if(newText!="</b>")
        {
            if(boldClickStatus) {
                boldClickStatus = false
                updateBoldText()
            }
        }else
        {
            if(!boldClickStatus) {
                boldClickStatus = true
                updateBoldText()
            }
        }
    }*/

    /*   fun updateUnlineDataBack(newText)
       {
           if(newText!="</u>")
           {
               if(underlineClickStatus) {
                   underlineClickStatus = false
                   ForUnderLineText()
               }
           }else
           {
               if(!underlineClickStatus) {
                   underlineClickStatus = true
                   ForUnderLineText()
               }
           }
       }*/

    //For UI Intialization
    private fun initUI() {
        styleArrayList = ArrayList<OptionSelectionData>()
=======
    //For UI Intialization
    private fun initUI() {
        styleArrayList=ArrayList<String>()
>>>>>>> origin/master
        setTextListener()
        dataBinding.apply {
            val arraylist = ArrayList<String>()
            arraylist.clear()
            arraylist.add("AA")
            arraylist.add("BB")
            arraylist.add("CC")
            arraylist.add("DD")
            arraylist.add("EE")
            arraylist.add("FF")
<<<<<<< HEAD
            arraylist.add("GG")
            arraylist.add("GG")
=======
>>>>>>> origin/master
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
<<<<<<< HEAD
                            /*    if (recoderTimeStemp < (mPlayer?.duration?.div(1000)
                                        ?: (30620 / 1000))
                                ) {
                                    recoderTimeStemp += 1
                                }*/
                            recoderTimeStemp += 1
//                            if (recoderTimeStemp < (mPlayer?.duration?.div(1000)
//                                    ?: (30620 / 1000))
//                            ) {
//                                recoderTimeStemp += 1
//                            } else
                            recoderTimeStemp += 1  // Comment this line if not working .
=======
                        /*    if (recoderTimeStemp < (mPlayer?.duration?.div(1000)
                                    ?: (30620 / 1000))
                            ) {
                                recoderTimeStemp += 1
                            }*/
                            recoderTimeStemp += 1
>>>>>>> origin/master
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

<<<<<<< HEAD
    fun updateBoldText() {
        dataBinding.etRichChatDemo.setBold()
        if (boldClickStatus) {
            Log.e("hthhthtt==", "added_bold")
            styleArrayList!!.add(OptionSelectionData("bold", "<b>"))
            dataBinding.imgBoldTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),
                R.color.blueColor))
        } else {
            Log.e("hthhthtt==", "Removed_bold")
            styleArrayList!!.remove(OptionSelectionData("bold", "<b>"))
            dataBinding.imgBoldTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),
                R.color.darkGrey))
=======
    fun updateBoldText()
    {
        dataBinding.etRichChatDemo.setBold()
        if(boldClickStatus){
            Log.e("hthhthtt==","added_bold")
            styleArrayList!!.add("bold")
            dataBinding.imgBoldTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))
        }else{
            Log.e("hthhthtt==","Removed_bold")
            styleArrayList!!.remove("bold")
            dataBinding.imgBoldTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))
>>>>>>> origin/master

        }
    }

<<<<<<< HEAD
    fun ForUnderLineText() {
        dataBinding.etRichChatDemo.setUnderline()
        if (underlineClickStatus) {
            //   styleArrayList!!.add("underline")
            styleArrayList!!.add(OptionSelectionData("underline", "<u>"))
            dataBinding.imgUnderLineTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                requireContext(),
                R.color.blueColor))
        } else {
            styleArrayList!!.remove(OptionSelectionData("underline", "<u>"))
            dataBinding.imgUnderLineTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                requireContext(),
                R.color.darkGrey))
=======
    fun ForUnderLineText()
    {
        dataBinding.etRichChatDemo.setUnderline()
        if(underlineClickStatus){
            styleArrayList!!.add("underline")
            dataBinding.imgUnderLineTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))
        }else{
            styleArrayList!!.remove("underline")
            dataBinding.imgUnderLineTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))
>>>>>>> origin/master

        }
    }

<<<<<<< HEAD
    fun setItalicForText() {
        dataBinding.etRichChatDemo.setItalic()
        if (italicClickStatus) {

            styleArrayList!!.add(OptionSelectionData("italic", "<i>"))
            dataBinding.imgItalicTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                requireContext(),
                R.color.blueColor))
        } else {
            styleArrayList!!.remove(OptionSelectionData("italic", "<i>"))
            dataBinding.imgItalicTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                requireContext(),
                R.color.darkGrey))

        }
    }

    fun forStrikeMethod() {
        dataBinding.etRichChatDemo.setStrikeThrough()
        if (strikeClickStatus) {
            styleArrayList!!.add(OptionSelectionData("strike", "<strike>"))
            //  styleArrayList!!.add("strike")
            dataBinding.imgStrikeTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                requireContext(),
                R.color.blueColor))

        } else {
            styleArrayList!!.remove(OptionSelectionData("strike", "<strike>"))
            //   styleArrayList!!.add("strike")
            dataBinding.imgStrikeTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                requireContext(),
                R.color.darkGrey))
=======
    fun setItalicForText()
    { dataBinding.etRichChatDemo.setItalic()
        if(italicClickStatus){
            styleArrayList!!.add("italic")
            dataBinding.imgItalicTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))
        }else{
            styleArrayList!!.remove("italic")
            dataBinding.imgItalicTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))

        }
    }

    fun forStrikeMethod()
    {
        dataBinding.etRichChatDemo.setStrikeThrough()
        if(strikeClickStatus){
            styleArrayList!!.add("strike")
            dataBinding.imgStrikeTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))

        }else{
            styleArrayList!!.add("strike")
            dataBinding.imgStrikeTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))
>>>>>>> origin/master
        }
    }

    private fun clickListner() {
        dataBinding.apply {
            imgBoldTxtIconChatDemo.setOnClickListener {
<<<<<<< HEAD
                boldClickStatus = !boldClickStatus
                updateBoldText()
            }
            imgItalicTxtIconChatDemo.setOnClickListener {
                italicClickStatus = !italicClickStatus
                setItalicForText()
            }
            imgUnderLineTxtIconChatDemo.setOnClickListener {
                underlineClickStatus = !underlineClickStatus
=======
                boldClickStatus=!boldClickStatus
                updateBoldText()
            }
            imgItalicTxtIconChatDemo.setOnClickListener {
                italicClickStatus=!italicClickStatus
                setItalicForText()
            }
            imgUnderLineTxtIconChatDemo.setOnClickListener {
                underlineClickStatus=!underlineClickStatus
>>>>>>> origin/master
                ForUnderLineText()
            }
            imgStrikeTxtIconChatDemo.setOnClickListener {

<<<<<<< HEAD
                strikeClickStatus = !strikeClickStatus
=======
                strikeClickStatus=!strikeClickStatus
>>>>>>> origin/master
                forStrikeMethod()
            }
            imgFormatListNumberTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setNumbers()
<<<<<<< HEAD
                numberParagraphClickStatus = !numberParagraphClickStatus
                if (numberParagraphClickStatus) {
                    imgFormatListNumberTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                } else {
                    imgFormatListNumberTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
=======
                numberParagraphClickStatus=!numberParagraphClickStatus
                if(numberParagraphClickStatus){
                    imgFormatListNumberTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))

                }else{
                    imgFormatListNumberTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))

>>>>>>> origin/master
                }
            }
            imgFormatListBulletedTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setBullets()
<<<<<<< HEAD
                bulletParagraphClickStatus = !bulletParagraphClickStatus
                if (bulletParagraphClickStatus) {
                    imgFormatListBulletedTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                } else {
                    imgFormatListBulletedTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
=======
                bulletParagraphClickStatus=!bulletParagraphClickStatus
                if(bulletParagraphClickStatus){
                    imgFormatListBulletedTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))

                }else{
                    imgFormatListBulletedTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))

>>>>>>> origin/master
                }
            }
            imgLeftAlignTxtIconChatDemo.setOnClickListener {
                viewModel.setAlignText(1)
                etRichChatDemo.setAlignLeft()
<<<<<<< HEAD
                /* leftAlignClickStatus=!leftAlignClickStatus
                 if(leftAlignClickStatus){
                     imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))

                 }else{
                     imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))

                 }*/
=======
               /* leftAlignClickStatus=!leftAlignClickStatus
                if(leftAlignClickStatus){
                    imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))

                }else{
                    imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))

                }*/
>>>>>>> origin/master
            }
            imgCenterAlignTxtIconChatDemo.setOnClickListener {
                viewModel.setAlignText(2)
                etRichChatDemo.setAlignCenter()
<<<<<<< HEAD
                /* centerAlignClickStatus=!centerAlignClickStatus
                 if(centerAlignClickStatus){
                     imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))

                 }else{
                     imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))

                 }*/
=======
               /* centerAlignClickStatus=!centerAlignClickStatus
                if(centerAlignClickStatus){
                    imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))

                }else{
                    imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))

                }*/
>>>>>>> origin/master
            }
            imgRightAlignTxtIconChatDemo.setOnClickListener {
                viewModel.setAlignText(3)
                etRichChatDemo.setAlignRight()
<<<<<<< HEAD
                /*    rightAlignClickStatus=!rightAlignClickStatus
                    if(rightAlignClickStatus){
                        imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))

                    }else{
                        imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))

                    }*/
            }
            imgEmojiIconChatDemo.setOnClickListener {
                val imm: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
=======
            /*    rightAlignClickStatus=!rightAlignClickStatus
                if(rightAlignClickStatus){
                    imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.blueColor))

                }else{
                    imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(requireContext(),R.color.darkGrey))

                }*/
            }
            imgEmojiIconChatDemo.setOnClickListener {
                val imm: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
>>>>>>> origin/master
                imm.showSoftInput(etRichChatDemo, InputMethodManager.SHOW_IMPLICIT)
            }
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
<<<<<<< HEAD
    fun showEditMessageMethod(ivMoreImageView: ImageView, userType: Int) {
=======
    fun showEditMessageMethod(ivMoreImageView: ImageView,userType:Int) {
>>>>>>> origin/master
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

        val editOtherMessageList = ArrayList<EditMessageData>()
        editOtherMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.reply_message_text),
            R.drawable.reply_message_icon))
<<<<<<< HEAD
        /* editOtherMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.edit_message_text),
             R.drawable.edit_message_icon))*/
=======
       /* editOtherMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.edit_message_text),
            R.drawable.edit_message_icon))*/
>>>>>>> origin/master
        editOtherMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.save_message_text),
            R.drawable.save_message))
        editOtherMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.mark_unread_text),
            R.drawable.mark_as_unread))
        editOtherMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.copy_message_text),
            R.drawable.copy_message_icon))
        editOtherMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.pin_conversation_text),
            R.drawable.push_pin))
        editOtherMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.delete_message_text),
            R.drawable.delete_chat_icon))

        val editOwnMessageList = ArrayList<EditMessageData>()
        editOwnMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.reply_message_text),
            R.drawable.reply_message_icon))
        editOwnMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.edit_message_text),
            R.drawable.edit_message_icon))
        editOwnMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.save_message_text),
            R.drawable.save_message))
<<<<<<< HEAD
        /* editOwnMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.mark_unread_text),
             R.drawable.mark_as_unread))*/
=======
       /* editOwnMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.mark_unread_text),
            R.drawable.mark_as_unread))*/
>>>>>>> origin/master
        editOwnMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.copy_message_text),
            R.drawable.copy_message_icon))
        editOwnMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.pin_conversation_text),
            R.drawable.push_pin))
        editOwnMessageList.add(EditMessageData(requireActivity().resources.getString(R.string.delete_message_text),
            R.drawable.delete_chat_icon))

        val newMessageList = ArrayList<EditMessageData>()
<<<<<<< HEAD
        if (userType == 0) {
            for (idx in 0 until editOtherMessageList.size) {
                newMessageList.add(editOtherMessageList[idx])
            }
        } else {
            for (idx in 0 until editOwnMessageList.size) {
=======
        if(userType==0)
        {
            for(idx in 0 until editOtherMessageList.size)
            {
                newMessageList.add(editOtherMessageList[idx])
            }
        }else
        {
            for(idx in 0 until editOwnMessageList.size)
            {
>>>>>>> origin/master
                newMessageList.add(editOwnMessageList[idx])
            }
        }

        val rvEditMessageAdapter =
            RVEditMessageAdapter(requireActivity(), newMessageList, object : ClickMessage {
                override fun onClickListener(position: Int) {
                    //For click on Edit Message Options
                    popUp.dismiss()
                }
            })
        val rvEditMessages = mView.findViewById<RecyclerView>(R.id.rvEditMessages)
        rvEditMessages.layoutManager = LinearLayoutManager(requireActivity())
        rvEditMessages.adapter = rvEditMessageAdapter
    }

<<<<<<< HEAD
    override fun onMesssageListener(position: Int, ivMoreImageView: ImageView, userType: Int) {
        showEditMessageMethod(ivMoreImageView, userType)
=======
    override fun onMesssageListener(position: Int, ivMoreImageView: ImageView,userType:Int) {
        showEditMessageMethod(ivMoreImageView,userType)
>>>>>>> origin/master
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