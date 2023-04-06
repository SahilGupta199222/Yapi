package com.yapi.views.add_people_email

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import com.yapi.R
import com.yapi.common.*
import com.yapi.databinding.FragmentAddPeopleEmailBinding
import com.yapi.views.add_people_email_confirmation.AddPeopleEmailConfirmationFragment
import com.yapi.views.sign_in.SignInErrorData


class AddPeopleEmailFragment : DialogFragment() {
    private lateinit var binding: FragmentAddPeopleEmailBinding
    private val viewModelAddPeopleEmail: ViewModelAddPeopleEmail by viewModels()

    companion object {
        fun newInstanceAddPeopleEmail(title: String): AddPeopleEmailFragment {
            val args = Bundle()
            args.putString("11", title)
            val fragment = AddPeopleEmailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(checkDeviceType()) {
            System.out.println("phone========tablet");
            setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenDialog)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddPeopleEmailBinding.inflate(LayoutInflater.from(requireActivity()))
        binding.model = viewModelAddPeopleEmail
        viewModelAddPeopleEmail.chipGroupAddPeopleEmail = binding.chipGroupAddPeopleEmail
        addObserverForOpenAddPeopleEmail()
        dialogDismissMethod()
        showErrorDataObserver()
        hideKeyboardDataMethod()

        setTouchListenereForNested()

        return binding.root
    }

    //For hide keyboard
    fun setTouchListenereForNested()
    {
        binding.nestedScrollViewAddPeopleEmail.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                requireActivity().hideKeyboard()
                return false
            }
        })
    }

    private fun hideKeyboardDataMethod() {
        viewModelAddPeopleEmail.hideKeyboardData.observe(requireActivity(),Observer{
            var data=it as Boolean
            if(data)
            {
                requireActivity().hideKeyboard()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setTopLayoutMethod()
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
                        binding.txtErrorEmailAddPeople.setText("")
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
        chip.closeIconTint =
            ColorStateList.valueOf(ContextCompat.getColor(context, com.yapi.R.color.darkLiteGrey))
        //.setTextSize(13f)
        chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected);
        chip.setPadding(paddingValue,paddingValue,paddingValue,paddingValue)

        binding.chipGroupAddPeopleEmail.addView(chip as View)
        chip.setOnCloseIconClickListener {
            binding.chipGroupAddPeopleEmail.removeView(chip as View)
        }
    }

    private fun addObserverForOpenAddPeopleEmail() {
        viewModelAddPeopleEmail.addPeopleEmailConfirmationOpenData.observe(requireActivity(), Observer {
            var data =it as Boolean
            if(data){
                AddPeopleEmailConfirmationFragment.newInstanceEmailConfirmation("").showNow(requireActivity().supportFragmentManager," SimpleDialog.TAG")
            }
        })
    }

    fun dialogDismissMethod()
    {
        viewModelAddPeopleEmail.dismissDialogData.observe(requireActivity(), Observer {
            var data=it as Boolean
            if(data)
            {
                dismiss()
            }
        })
    }


    fun setTopLayoutMethod()
    {
        var rightMarginTopLayout=0
        if(checkDeviceType())
        {
            rightMarginTopLayout=requireActivity().resources.getDimension(com.intuit.sdp.R.dimen._18sdp).toInt()
            binding.ivOutsideCloseAddPeopleEmail.visibility=View.VISIBLE
            binding.imgCancelAddPeopleEmail.visibility=View.GONE
            binding.layoutAddPeopleEmail.setBackgroundResource(R.drawable.et_drawable)
        }else
        {
            binding.layoutAddPeopleEmail.setBackgroundResource(0)
            rightMarginTopLayout=0
            binding.ivOutsideCloseAddPeopleEmail.visibility=View.GONE
            binding.imgCancelAddPeopleEmail.visibility=View.VISIBLE
        }
        val layoutParams = binding.layoutAddPeopleEmail.layoutParams as LinearLayout.LayoutParams
        //  val newLayoutParams = toolbar.getLayoutParams()
        layoutParams.topMargin = 0
        layoutParams.leftMargin = 0
        layoutParams.rightMargin = rightMarginTopLayout
        binding.layoutAddPeopleEmail.setLayoutParams(layoutParams)
    }

    fun showErrorDataObserver()
    {
        viewModelAddPeopleEmail.errorData.observe(requireActivity(), Observer {
            var data=it as SignInErrorData
            if(data!=null && data.message!="")
            {
                binding.txtErrorEmailAddPeople.setText(data.message)
                changeBackgroundForEditError(binding.etChipAddPeopleEmail!!,requireActivity().resources.getColor(
                    R.color.error_box_color),
                    requireActivity().resources.getColor(R.color.error_border_color))
            }else
            {
                binding.txtErrorEmailAddPeople.setText("")
                changeBackgroundForEditError(binding.etChipAddPeopleEmail!!, requireActivity().resources.getColor(
                    R.color.white),
                    requireActivity().resources.getColor(R.color.liteGrey))
            }
        })
    }
}