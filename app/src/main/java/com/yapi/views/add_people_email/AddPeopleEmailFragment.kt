package com.yapi.views.add_people_email

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.yapi.R
import com.yapi.common.isEmailValid
import com.yapi.databinding.FragmentAddPeopleEmailBinding


class AddPeopleEmailFragment : Fragment() {
    private lateinit var binding: FragmentAddPeopleEmailBinding
    private val viewModelAddPeopleEmail: ViewModelAddPeopleEmail by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddPeopleEmailBinding.inflate(LayoutInflater.from(requireActivity()))
        binding.model = viewModelAddPeopleEmail
        viewModelAddPeopleEmail.chipGroupAddPeopleEmail = binding.chipGroupAddPeopleEmail
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            etChipAddPeopleEmail.doOnTextChanged { text, start, before, count ->
                if (text?.isNotEmpty() == true) {
                    layoutAddPeopleAddPeopleEmail.visibility = View.VISIBLE
                    txtAddPeopleAddPeopleEmail.text = text
                    txtUserNameAddPeopleEmail.text = text[0].toString()
                } else {
                    layoutAddPeopleAddPeopleEmail.visibility = View.GONE
                }
            }
            layoutAddPeopleAddPeopleEmail.setOnClickListener {
                if (etChipAddPeopleEmail.text?.isNotEmpty() == true) {
                    val msg = requireActivity().isEmailValid(etChipAddPeopleEmail.text.toString())
                    if (msg.isEmpty()) {
                        addChipToGroup(requireContext(), etChipAddPeopleEmail.text.toString())
                        layoutAddPeopleAddPeopleEmail.visibility = View.GONE
                        etChipAddPeopleEmail.text?.clear()
                    } else {
                        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun addChipToGroup(context: Context, person: String) {
        val chip = Chip(context)
        chip.text = person
        chip.setTextColor(ContextCompat.getColor(context, R.color.blueColor))
        chip.chipBackgroundColor =
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.liteBlueForDrawable))
        chip.chipCornerRadius = context.resources.getDimension(com.intuit.sdp.R.dimen._5sdp)
        chip.isCloseIconVisible = true
        chip.closeIcon =
            ContextCompat.getDrawable(context, R.drawable.ic_cross_icon)
        chip.closeIconSize=context.resources.getDimension(com.intuit.sdp.R.dimen._8sdp)
      var paddingValue= context.resources.getDimension(com.intuit.sdp.R.dimen._5sdp).toInt()
        chip.isCheckable = false
        chip.setPadding(paddingValue,paddingValue,paddingValue,paddingValue)
        chip.closeIconTint =
            ColorStateList.valueOf(ContextCompat.getColor(context, com.yapi.R.color.darkLiteGrey))

        binding.chipGroupAddPeopleEmail.addView(chip as View)
        chip.setOnCloseIconClickListener {
            binding.chipGroupAddPeopleEmail.removeView(chip as View)
        }
    }

}