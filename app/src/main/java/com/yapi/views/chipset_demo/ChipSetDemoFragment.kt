package com.yapi.views.chipset_demo

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.core.widget.doOnTextChanged
import com.google.android.material.chip.Chip
import com.yapi.R
import com.yapi.common.getTextSizeValue
import com.yapi.databinding.FragmentAddPeopleBinding
import com.yapi.databinding.FragmentChipSetDemoBinding


class ChipSetDemoFragment : Fragment() {
    private lateinit var binding:FragmentChipSetDemoBinding
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
            etChips.doOnTextChanged { text, start, before, count ->
                if(text?.isNotEmpty() == true ){
                    layoutAddPeople.visibility=View.VISIBLE
                    txtAddPeopleChip.text=text
                    txtUserNameChip.text= text[0].toString()
                }else{
                    layoutAddPeople.visibility=View.GONE
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
//        chip.chipIconTint= myList
        chip.isCloseIconVisible=true
        chip.closeIcon=ContextCompat.getDrawable(context, com.hbb20.R.drawable.abc_ic_clear_material)
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