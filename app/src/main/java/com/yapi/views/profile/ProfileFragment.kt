package com.yapi.views.profile

import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.Constants
import com.yapi.common.checkDeviceType
import com.yapi.databinding.FragmentProfileBinding
import com.yapi.pref.PreferenceFile
import com.yapi.views.create_group.CreateGroupFragment
import com.yapi.views.edit_profile.EditProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : DialogFragment() {
    private lateinit var binding: com.yapi.databinding.FragmentProfileBinding
    private val viewModel: ViewModelProfile by viewModels()

    @Inject
    lateinit var preferenceFile: PreferenceFile

    companion object {
        fun newInstanceProfileScreen(title: String): ProfileFragment {
            val args = Bundle()
            args.putString("11", title)
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create a new dialog
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)

        // Get the window of the dialog
        val window: Window = dialog.getWindow()!!

        // Set the dialog to be shown at the bottom of the screen
        window.setGravity(Gravity.RIGHT)

        var second_frame_height= preferenceFile.fetchStringValue("second_frame_height")
        var second_frame_width=  preferenceFile.fetchStringValue("second_frame_width")
        Log.e("nefjkwnddfkewfwefe===",second_frame_height+"==="+second_frame_width)
       //window.setLayout(second_frame_width.toInt(),second_frame_height.toInt())
        return dialog
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
        Log.e("gmkrmddeeeegkrgrg===","onCreateView")
        if(Constants.API_CALL_DEMO) {
            showProfileResponseObserver()
        }else
        {
            viewModel.topProfileVisibility.set(true)
            viewModel.nameValue.set("test")
            viewModel.userNameValue.set("@test")
            viewModel.emailValue.set("test1@gmail.com")
            viewModel.emailVisiblityValue.set(true)

            viewModel.aboutVisiblityValue.set(true)
            viewModel.aboutTitle.set(MainActivity.activity!!.get()!!.resources.getString(R.string.about))
            viewModel.aboutValue.set("Dignissim cras tincidunt lobortis feugiat vivamus at augue eget arcu. Lobortis mattis aliquam faucibus purus in massa")

            viewModel.phoneValue.set("+91 1234567890")
            viewModel.phoneVisiblityValue.set(true)
            viewModel.phoneTitle.set(MainActivity.activity!!.get()!!.resources.getString(R.string.phone_textt))

            viewModel.roleVisiblityValue.set(true)
            viewModel.roleTitle.set(MainActivity.activity!!.get()!!.resources.getString(R.string.role))
            viewModel.roleValue.set("Workspace owner")

            viewModel.regionVisiblityValue.set(true)
            viewModel.regionTitle.set(MainActivity.activity!!.get()!!.resources.getString(R.string.region))
            viewModel.regionValue.set("(UTC-08:00) Pacific Time (US and Canada)")
        }
        return binding.root

    }

    private fun showProfileResponseObserver() {
        viewModel.fetchProfileData()
    }

    private fun addNextToScreenObserver() {
        viewModel.openEditProfileData.observe(requireActivity(), Observer {
            var data = it as ProfileData
            if (data!=null) {
                EditProfileFragment.newInstanceEditProfileScreen("",data)
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