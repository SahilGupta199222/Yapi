package com.yapi.views.chipset_demo

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.core.widget.doOnTextChanged
import com.google.android.material.chip.Chip
import com.yapi.R
import com.yapi.common.getTextSizeValue
import com.yapi.databinding.FragmentAddPeopleBinding
import com.yapi.databinding.FragmentChipSetDemoBinding
import jp.wasabeef.richeditor.RichEditor


class ChipSetDemoFragment : Fragment() {
    private lateinit var binding:FragmentChipSetDemoBinding
    private var fontStyleSelected=false
    private var boldTxtSelected=false
    private var italicTxtSelected=false
    private var underLineTxtSelected=false
    private var strikeTxtSelected=false
    private var listNumberTxtSelected=false
    private var listBulletTxtSelected=false
    private var leftAlignTxtSelected=false
    private var rightAlignTxtSelected=false
    private var centerAlignTxtSelected=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentChipSetDemoBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            etRichChatDemo.setPlaceholder("Enter msg here")
//            etRichChatDemo.hint = "Enter msg here"
            imgTxtStyleChangeIconChatDemo.setOnClickListener {
                fontStyleSelected=!fontStyleSelected
                if (fontStyleSelected) {
                    imgTxtStyleChangeIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                    layoutFontStylesOfEditTextDemoChat.visibility = View.VISIBLE
                    viewLineFontStylesEditTextDemoChat.visibility = View.VISIBLE
                }
                else {
                    imgTxtStyleChangeIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.medium_grey_color))
                    layoutFontStylesOfEditTextDemoChat.visibility = View.GONE
                    viewLineFontStylesEditTextDemoChat.visibility = View.GONE
                }
            }
            imgBoldTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setBold()
//                etRichChatDemo.focusEditor()
//                etRichChatDemo.updateTextStyle(EditorTextStyle.BOLD)
                boldTxtSelected=!boldTxtSelected
                if (boldTxtSelected) {
                    imgBoldTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                }
                else {
                    imgBoldTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgItalicTxtIconChatDemo.setOnClickListener {
                etRichChatDemo.setItalic()
//                etRichChatDemo.focusEditor()

                italicTxtSelected=!italicTxtSelected
                if (italicTxtSelected) {
                    imgItalicTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                }
                else {
                    imgItalicTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgUnderLineTxtIconChatDemo.setOnClickListener {
                    etRichChatDemo.setUnderline()
//                etRichChatDemo.focusEditor()
                underLineTxtSelected=!underLineTxtSelected
                if (underLineTxtSelected) {
                    imgUnderLineTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                }
                else {
                    imgUnderLineTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgStrikeTxtIconChatDemo.setOnClickListener {
                    etRichChatDemo.setStrikeThrough()
//                etRichChatDemo.focusEditor()
                strikeTxtSelected=!strikeTxtSelected

                if (strikeTxtSelected) {
                    imgStrikeTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                }
                else {
                    imgStrikeTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgFormatListNumberTxtIconChatDemo.setOnClickListener {
//                etRichChatDemo.focusEditor()
                    etRichChatDemo.setNumbers()
                listNumberTxtSelected=!listNumberTxtSelected

                if (listNumberTxtSelected) {
                    imgFormatListNumberTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                }
                else {
                    imgFormatListNumberTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgFormatListBulletedTxtIconChatDemo.setOnClickListener {
                    etRichChatDemo.setBullets()
                listBulletTxtSelected=!listBulletTxtSelected
                if (listBulletTxtSelected) {
                    imgFormatListBulletedTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                }
                else {
                    imgFormatListBulletedTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgLeftAlignTxtIconChatDemo.setOnClickListener {
                    etRichChatDemo.setAlignLeft()
                leftAlignTxtSelected=!leftAlignTxtSelected
                if (leftAlignTxtSelected) {
                    imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                }
                else {
                    imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if(centerAlignTxtSelected){
                    centerAlignTxtSelected=false
                    imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if(rightAlignTxtSelected){
                    rightAlignTxtSelected=false
                    imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgCenterAlignTxtIconChatDemo.setOnClickListener {
                    etRichChatDemo.setAlignCenter()
                centerAlignTxtSelected=!centerAlignTxtSelected
                if (centerAlignTxtSelected) {
                    imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                }
                else {
                    imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if(leftAlignTxtSelected){
                    leftAlignTxtSelected=false
                    imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if(rightAlignTxtSelected){
                    rightAlignTxtSelected=false
                    imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            imgRightAlignTxtIconChatDemo.setOnClickListener {
                    etRichChatDemo.setAlignRight()
                rightAlignTxtSelected=!rightAlignTxtSelected
                if (rightAlignTxtSelected) {
                    imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.blueColor))
                }
                else {
                    imgRightAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if(leftAlignTxtSelected){
                    leftAlignTxtSelected=false
                    imgLeftAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
                if(centerAlignTxtSelected){
                    centerAlignTxtSelected=false
                    imgCenterAlignTxtIconChatDemo.setColorFilter(ContextCompat.getColor(
                        requireContext(),
                        R.color.darkGrey))
                }
            }
            etChips.doOnTextChanged { text, start, before, count ->
                if(text?.isNotEmpty() == true ){
                    layoutAddPeople.visibility=View.VISIBLE
                    txtAddPeopleChip.text=text
                    txtUserNameChip.text= text[0].toString()
                }else{
                    layoutAddPeople.visibility=View.GONE
                    etRichChatDemo.removeFormat()
                }
            }
            layoutAddPeople.setOnClickListener {
                if(etChips.text?.isNotEmpty()==true){
                    addChipToGroup(requireContext(),etChips.text.toString())
                    layoutAddPeople.visibility=View.GONE
                    etChips.text?.clear()
                    chipGroup.visibility=View.VISIBLE
                }
            }
            imgLinkIconChatDemo.setOnClickListener {
                etRichChatDemo.insertLink("https://cdn.britannica.com/49/182849-050-4C7FE34F/scene-Iron-Man.jpg","iron man link")
            }

            etRichChatDemo.setOnDecorationChangeListener { text, types ->
                Log.i("asdfjnasdf","i ->${types}  and text  $text\n")
            }
            etRichChatDemo.setOnDecorationChangeListener(object : RichEditor.OnDecorationStateListener{
                override fun onStateChangeListener(
                    text: String?,
                    types: MutableList<RichEditor.Type>?,
                ) {
                }


            })



        }
    }

    fun addChipToGroup(context: Context, person: String) {
        val chip = Chip(context)
        chip.text = person
        chip.setTextColor(ContextCompat.getColor(context,R.color.blueColor))
//        chip.textSize=     context.resources.getDimension(R.dimen.normalTextSize)
        chip.chipBackgroundColor= ColorStateList.valueOf(ContextCompat.getColor(context, R.color.liteBlueForDrawable))
        chip.chipCornerRadius=context.resources.getDimension(com.intuit.sdp.R.dimen._5sdp)
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
        chip.isCloseIconVisible=true
//        chip.chipIconTint= myList
        chip.closeIcon=ContextCompat.getDrawable(context, R.drawable.ic_cross_icon)
        chip.isCheckable = false
        binding.chipGroup.addView(chip as View)
//        chip.chipStrokeColor= ColorStateList.valueOf(ContextCompat.getColor(context, R.color.normaltxtColor))
//        chip.chipStrokeWidth=2.0f
        chip.setOnCloseIconClickListener { binding.chipGroup.removeView(chip as View)
//        if(binding.chipGroup.size>0){
//        binding.chipGroup.visibility=View.VISIBLE
//        }else{
//        binding.chipGroup.visibility=View.GONE
//        }
        }
    }

}