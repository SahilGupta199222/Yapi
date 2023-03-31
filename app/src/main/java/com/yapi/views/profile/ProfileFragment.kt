package com.yapi.views.profile

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.databinding.FragmentProfileBinding
import com.yapi.views.create_group.CreateGroupFragment
import com.yapi.views.edit_profile.EditProfileFragment

class ProfileFragment : DialogFragment() {
    private lateinit var binding: com.yapi.databinding.FragmentProfileBinding
    private val viewModel: ViewModelProfile by viewModels()

    companion object {
        fun newInstanceProfileScreen(title: String): ProfileFragment {
            val args = Bundle()
            args.putString("11", title)
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (checkDeviceType()) {
            System.out.println("phone========tablet")
            setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenDialog)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            com.yapi.databinding.FragmentProfileBinding.inflate(LayoutInflater.from(requireActivity()))
        binding.model = viewModel

        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels
        viewModel.screenWidth = width
        addNextToScreenObserver()
        dismissDialogMethodObserver()
        setTopLayoutMethod()
        return binding.root
    }

    private fun addNextToScreenObserver() {

        viewModel.openEditProfileData.observe(requireActivity(), Observer {
            var data = it as Boolean
            if (data) {
                EditProfileFragment.newInstanceEditProfileScreen("")
                    .showNow(requireActivity().supportFragmentManager, "")
            }
        })
    }

    private fun dismissDialogMethodObserver() {

        viewModel.dismissDialogData.observe(requireActivity(), Observer {
            var data = it as Boolean
            if (data) {
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
            binding.ivOutsideCloseProfile.visibility=View.VISIBLE
            binding.imgCancelProfile.visibility=View.GONE
            binding.layoutProfile.setBackgroundResource(R.drawable.et_drawable)
        }else
        {
            binding.layoutProfile.setBackgroundResource(0)
            rightMarginTopLayout=0
            binding.ivOutsideCloseProfile.visibility=View.GONE
            binding.imgCancelProfile.visibility=View.VISIBLE
        }
        val layoutParams = binding.layoutProfile.layoutParams as LinearLayout.LayoutParams
        //  val newLayoutParams = toolbar.getLayoutParams()
        layoutParams.topMargin = 0
        layoutParams.leftMargin = 0
        layoutParams.rightMargin = rightMarginTopLayout
        binding.layoutProfile.setLayoutParams(layoutParams)
    }
}