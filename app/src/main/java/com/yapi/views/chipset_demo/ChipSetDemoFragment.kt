package com.yapi.views.chipset_demo

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.icu.lang.UProperty.INT_START
import android.os.Bundle
import android.text.Html
import android.text.Layout
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.KeyListener
import android.text.style.AlignmentSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.yapi.R
import com.yapi.databinding.FragmentChipSetDemoBinding
import jp.wasabeef.richeditor.RichEditor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import org.jsoup.Jsoup
import java.awt.font.TextAttribute.UNDERLINE


class ChipSetDemoFragment : Fragment() {
    private lateinit var binding: FragmentChipSetDemoBinding
    private var fontStyleSelected = false
    private var boldTxtSelected = false
    private var italicTxtSelected = false
    private var underLineTxtSelected = false
    private var strikeTxtSelected = false
    private var listNumberTxtSelected = false
    private var listBulletTxtSelected = false
    private var leftAlignTxtSelected = false
    private var rightAlignTxtSelected = false
    private var centerAlignTxtSelected = false
    private var before = 0
    private var boldStatus = 0
    private var tempText = ""
    private var lastCharacter = ""
    private var lastIndex = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChipSetDemoBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        binding.apply {
            imgEmojiIconChatDemo.setOnClickListener {
                Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init() {
        Log.i("asdfjnasdf", "init fucntion called")
        binding.apply {
            etRichChatDemo.setPlaceholder("Enter msg here")
//            etRichChatDemo.hint = "Enter msg here"
            imgTxtStyleChangeIconChatDemo.setOnClickListener {
                fontStyleSelected = !fontStyleSelected
                if (fontStyleSelected) {
                    imgTxtStyleChangeIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                    layoutFontStylesOfEditTextDemoChat.visibility = View.VISIBLE
                    viewLineFontStylesEditTextDemoChat.visibility = View.VISIBLE
                } else {
                    imgTxtStyleChangeIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.medium_grey_color))
                    layoutFontStylesOfEditTextDemoChat.visibility = View.GONE
                    viewLineFontStylesEditTextDemoChat.visibility = View.GONE
                }
            }
            imgBoldTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setBold()
//                CoroutineScope(Dispatchers.Main).async {
//                    etRichChatDemo.notifyAll()
//                    etRichChatDemo.performClick();
//                etRichChatDemo.notifySubtreeAccessibilityStateChanged(it,it,1)
//
//                }
//                etRichChatDemo.focusEditor()
//                etRichChatDemo.updateTextStyle(EditorTextStyle.BOLD)
                boldTxtSelected = !boldTxtSelected
                if (boldTxtSelected) {
                    imgBoldTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                    boldStatus = 1

                } else {
                    imgBoldTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                    boldStatus = 2
                }
            }
            imgItalicTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setItalic()
//                etRichChatDemo.focusEditor()

                italicTxtSelected = !italicTxtSelected
                if (italicTxtSelected) {
                    imgItalicTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))

                } else {
                    imgItalicTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgUnderLineTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setUnderline()
//                etRichChatDemo.focusEditor()
                underLineTxtSelected = !underLineTxtSelected
                if (underLineTxtSelected) {
                    imgUnderLineTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                } else {
                    imgUnderLineTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgStrikeTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setStrikeThrough()
//                etRichChatDemo.focusEditor()
                strikeTxtSelected = !strikeTxtSelected

                if (strikeTxtSelected) {
                    imgStrikeTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                } else {
                    imgStrikeTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgFormatListNumberTxtIconChatDemo.setOnClickListener {
//                etRichChatDemo.focusEditor()
                etRichChatDemo.setNumbers()
                listNumberTxtSelected = !listNumberTxtSelected

                if (listNumberTxtSelected) {
                    imgFormatListNumberTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                } else {
                    imgFormatListNumberTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgFormatListBulletedTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setBullets()
                listBulletTxtSelected = !listBulletTxtSelected
                if (listBulletTxtSelected) {
                    imgFormatListBulletedTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                } else {
                    imgFormatListBulletedTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgLeftAlignTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setAlignLeft()
                leftAlignTxtSelected = !leftAlignTxtSelected
                if (leftAlignTxtSelected) {
                    imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                } else {
                    imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if (centerAlignTxtSelected) {
                    centerAlignTxtSelected = false
                    imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if (rightAlignTxtSelected) {
                    rightAlignTxtSelected = false
                    imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgCenterAlignTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setAlignCenter()
                centerAlignTxtSelected = !centerAlignTxtSelected
                if (centerAlignTxtSelected) {
                    imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                } else {
                    imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if (leftAlignTxtSelected) {
                    leftAlignTxtSelected = false
                    imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if (rightAlignTxtSelected) {
                    rightAlignTxtSelected = false
                    imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgRightAlignTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setAlignRight()
                rightAlignTxtSelected = !rightAlignTxtSelected
                if (rightAlignTxtSelected) {
                    imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                } else {
                    imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(), R.color.darkGrey))
                }
                if (leftAlignTxtSelected) {
                    leftAlignTxtSelected = false
                    imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if (centerAlignTxtSelected) {
                    centerAlignTxtSelected = false
                    imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            etChips.doOnTextChanged { text, start, before, count ->
                if (text?.isNotEmpty() == true) {
                    layoutAddPeople.visibility = View.VISIBLE
                    txtAddPeopleChip.text = text
                    txtUserNameChip.text = text[0].toString()
                } else {
                    layoutAddPeople.visibility = View.GONE
//                    etRichChatDemo.removeFormat()
                }
            }
            layoutAddPeople.setOnClickListener {
                if (etChips.text?.isNotEmpty() == true) {
                    addChipToGroup(requireContext(), etChips.text.toString())
                    layoutAddPeople.visibility = View.GONE
                    etChips.text?.clear()
                    chipGroup.visibility = View.VISIBLE
                }
            }
            imgLinkIconChatDemo.setOnClickListener {
                tvMessages.text = tempText.toString()
//                etRichChatDemo.setText(Html.fromHtml(tempText))
//                tvMessages.text=etRichChatDemo.lineCount.toString()
//                etRichChatDemo.insertLink("https://cdn.britannica.com/49/182849-050-4C7FE34F/scene-Iron-Man.jpg","iron man link")
            }
            etRichChatDemo.setOnDecorationChangeListener(object :
                RichEditor.OnDecorationStateListener {
                override fun onStateChangeListener(
                    text: String?,
                    types: MutableList<RichEditor.Type>?,
                ) {
                    Log.i("fhycdfhfdrfdc", "decoration changed $text\ntypes$types")
                }

            })

            etRichChatDemo.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                    if (event!!.action == KeyEvent.KEYCODE_BACK || event.action == KeyEvent.KEYCODE_DEL) {
                        if (tempText[tempText.length - 2].equals(">") && tempText[tempText.length - 3].equals(
                                "/") && tempText[tempText.length - 4].equals("<")
                        ) {
                            tempText.removeRange(tempText.length - 4, tempText.length)

                        } else if (tempText[tempText.length - 1].equals(lastCharacter)) {
                            tempText.removeRange(tempText.length - 2, tempText.length)
                            lastCharacter = tempText[tempText.length - 1].toString()
                        } else
                            return true
                    }
                    return false
                }
            })

            /*      etRichChatDemo.doOnTextChanged { text, start, before, count ->
                      if(etRichChatDemo.text.toString().trim().isNotEmpty()) {
                          val value=etRichChatDemo.text.toString()
                          lastCharacter = value[start].toString()
                      }
                      if(etRichChatDemo.text.toString().trim().isEmpty()){
                          tempText=""
                      }
                      if(boldStatus==1){
                          tempText+="<b>"
                          boldStatus=0
        0              }else if(boldStatus==2){
                          tempText+="</b>"
                          boldStatus=0
                      }
                      else{
                          tempText+= text?.get(start)?:""
                      }
                      Log.i("damanpreet","text$text\nstart$start,before $before\ncount$count")
                      etRichChatDemo.setText(Html.fromHtml(etRichChatDemo.text.toString().trim()))
                  }*/
            etRichChatDemo.setOnTextChangeListener(object : RichEditor.OnTextChangeListener {
                override fun onTextChange(textt: String?) {
                    val text1 = etRichChatDemo.html
                    Log.i("fhycdfhfdrfdc", "text $textt")
                    if (textt?.length!! > 0) {
                        val text = textt[textt.length - 1].toString()
                        if (text.isNotEmpty()) {
                            val doc = Jsoup.parse(textt)
                            val lis = doc.select(text).first()
                            val t = lis?.text()
//                            val  tt=t?.lastIndexOf(text)
//                            val lis = doc.select(text.toString())
                            Log.i("fhycdfhfdrfdc", "textS ${t}")
                        }
//                    var abc=text
//                    Log.d("fhycdfhfdrfdc",abc.toString())
                        val spannable =
                            SpannableStringBuilder.valueOf(Html.fromHtml(text.toString()))
                        val spans = spannable.getSpans(0, spannable.length, StyleSpan::class.java)
                        if (spans.isNotEmpty()) {
                            val value = spans[0]
                            when (value.style) {
                                Typeface.BOLD_ITALIC -> {
                                    Log.i("fhycdfhfdrfdc", "bold italic span apply")

                                }
                                Typeface.BOLD -> {
                                    Log.i("fhycdfhfdrfdc", "bold span apply")

                                }
                                Typeface.ITALIC -> {
                                    Log.i("fhycdfhfdrfdc", "italic span apply")

                                }
                            }
                        }
                    }
//                    val alignmentSpans = spannable.getSpans(0, spannable.length, AlignmentSpan::class.java)
//
//                    if (alignmentSpans.isNotEmpty()) {
//                        // Get the last applied AlignmentSpan (since alignment can change multiple times)
//                        val lastAlignmentSpan = alignmentSpans.last()
//
//                        // Check the alignment value of the lastAlignmentSpan
//                        when (lastAlignmentSpan.alignment) {
//                            Layout.Alignment.ALIGN_CENTER -> {
//                                // Handle center-aligned text
//                            }
//                            Layout.Alignment.ALIGN_OPPOSITE -> {
//                                // Handle right-aligned text
//                            }
//                            else -> {
//                                // Handle left-aligned text (default)
//                            }
//                        }
//                    } else {
//                        // Handle left-aligned text (default)
//                    }
//                    val underlineSpans = spannable.getSpans(0, spannable.length, UnderlineSpan::class.java)
//                    val isUnderlined = underlineSpans.isNotEmpty()
//                    if (isUnderlined) {
//                        Log.i("fhycdfhfdrfdc", "underLine span apply")
//                        // Handle underlined text
//                    }
//                    val strikeThroughSpans = spannable.getSpans(0, spannable.length, StrikethroughSpan::class.java)
//                    val isStrikethrough = strikeThroughSpans.isNotEmpty()
//                    if (isStrikethrough) {
//                        // Handle strikethrough text
//                        Log.i("fhycdfhfdrfdc", "StrikeThrough span apply")
//                    }
//                    if (value.style == Typeface.BOLD) {
//                        Log.i("fhycdfhfdrfdc","bold span apply")
//                        // Handle bold text
//                    }
//                    if (styleSpan.style == Typeface.ITALIC) {
//                        Log.i("fhycdfhfdrfdc","italic span apply")
//                        // Handle italic text
//                    }
                }

            })

//            etRichChatDemo.doAfterTextChanged {
//                val text=etRichChatDemo.text
//                val count=etRichChatDemo.text?.length
//                etRichChatDemo.text?.clear()
//                val str = SpannableStringBuilder(text)
//                if(boldTxtSelected && italicTxtSelected) {
//                    str.setSpan(StyleSpan(Typeface.BOLD_ITALIC),
//                        before,
//                        count?:0,
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                }
//                else if(boldTxtSelected) {
//                    str.setSpan(StyleSpan(Typeface.BOLD),
//                        before,
//                        count?:0,
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                }
//                else if(italicTxtSelected) {
//                    str.setSpan(StyleSpan(Typeface.ITALIC),
//                        before,
//                        count?:0,
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                }
//                Log.i("asdfiasdjkfn","Spannable string is ->$str")
//                etRichChatDemo.text=str
//            }
            imgSendIconChatDemo.setOnClickListener {
//                tvMessages.text= Html.fromHtml(etRichChatDemo.html.toString())
            }
//            etRichChatDemo.doOnTextChanged { text, start, beforee, count ->
//                before=beforee
//                Log.i("asdfiasdjkfn","Start $start, before $before , count $count")
////                val str = SpannableStringBuilder(text)
////                if(boldTxtSelected && italicTxtSelected) {
////                    str.setSpan(StyleSpan(Typeface.BOLD_ITALIC),
////                        before,
////                        count,
////                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
////                }
////                else if(boldTxtSelected) {
////                    str.setSpan(StyleSpan(Typeface.BOLD),
////                        before,
////                        count,
////                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
////                }
////                else if(italicTxtSelected) {
////                    str.0setSpan(StyleSpan(Typeface.ITALIC),
////                        be0fore,
////                        coun0t,
////                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
////                }
////                Log.i("asdfiasdjkfn","Spannable string is ->$str")
////                etRichChatDemo.text=str
//            }
//            etRichChatDemo.setOnTextChangeListener { object:RichEditor.OnTextChangeListener{
//                override fun onTextChange(text: String?) {
//                    Log.i("asdfjnasdf","OnText Chage listner ${text}\n")
//                    etRichChatDemo.performClick()
//                }
//
//            } }

//            etRichChatDemo.setOnDecorationChangeListener { text, types ->
//                Log.i("asdfjnasdf","i ->${types}  and text  $text\n")
//            }
        }
    }

    fun addChipToGroup(context: Context, person: String) {
        val chip = Chip(context)
        chip.text = person
        chip.setTextColor(ContextCompat.getColor(context, R.color.blueColor))
//        chip.textSize=     context.resources.getDimension(R.dimen.normalTextSize)
        chip.chipBackgroundColor =
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.liteBlueForDrawable))
        chip.chipCornerRadius = context.resources.getDimension(com.intuit.sdp.R.dimen._5sdp)
//        val states = arrayOf(
//            intArrayOf(android.R.attr.state_enabled), // enabled
//            intArrayOf(-android.R.attr.state_enabled), // disabled
//            intArrayOf(-android.R.attr.state_checked), // unchecked
//            intArrayOf(android.R.attr.state_pressed)  // pressed
//        )
//        val colors = intArrayOf(
//            ContextCompat.getColor(context,R.color.darkLiteGrey)
//        )
//        val myList = ColorStateList(states, colors)
        chip.isCloseIconVisible = true
//        chip.chipIconTint= myList
        chip.closeIcon = ContextCompat.getDrawable(context, R.drawable.ic_cross_icon)
        chip.isCheckable = false
        binding.chipGroup.addView(chip as View)
//        chip.chipStrokeColor= ColorStateList.valueOf(ContextCompat.getColor(context, R.color.normaltxtColor))
//        chip.chipStrokeWidth=2.0f
        chip.setOnCloseIconClickListener {
            binding.chipGroup.removeView(chip as View)
//        if(binding.chipGroup.size>0){
//        binding.chipGroup.visibility=View.VISIBLE
//        }else{
//        binding.chipGroup.visibility=View.GONE
//        }
        }
    }


}