package com.yapi.views.add_people_email_confirmation

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.databinding.FragmentAddPeopleBinding
import com.yapi.databinding.FragmentAddPeopleEmailConfirmationBinding
import com.yapi.pref.PreferenceFile
import com.yapi.views.create_group.CreateGroupFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddPeopleEmailConfirmationFragment : DialogFragment() {
    private lateinit var binding:FragmentAddPeopleEmailConfirmationBinding
    private val viewModel:ViewModelAddPeopleEmailConfirmation by viewModels()

    @Inject
    lateinit var preferenceFile: PreferenceFile

    //add_email_confirmation_logo
    companion object {
        fun newInstanceEmailConfirmation(title: String): AddPeopleEmailConfirmationFragment {
            val args = Bundle()
            args.putString("11", title)
            val fragment = AddPeopleEmailConfirmationFragment()
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
        window.setLayout(second_frame_width.toInt(),second_frame_height.toInt())
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding=FragmentAddPeopleEmailConfirmationBinding.inflate(LayoutInflater.from(requireContext()))
        binding.model=viewModel
        dialogDismissMethod()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setList()
        setTopLayoutMethod()
        binding.apply {

        }
    }

    private fun setList() {
        val list=arguments?.getStringArrayList("personList")
        var listCount="0"
        if(list?.isNotEmpty()==true){
           listCount= list.size.toString()
            binding.rvEmailConfirmationOfAddPeopleEmailConf.adapter=AdapterEmailConfirmation(requireContext(),list)
        }else
        {
            listCount= "0"
        }
        var countValue=requireActivity().getString(R.string.you_have_invited)+" "+listCount+" "+requireActivity().getString(R.string.one_person)
        viewModel.invitedPersonCount.set(countValue)
    }

    fun dialogDismissMethod()
    {
        viewModel.dismissDialogData.observe(requireActivity(), Observer {
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
            binding.ivOutsideCloseAddEmailConfirmation.visibility=View.VISIBLE
            binding.imgCancelAddPeopleEmailConf.visibility=View.GONE
            binding.constraintsEmailConfirm.setBackgroundResource(R.drawable.et_drawable)

            binding.imgBannerAddPeopleEmailConf.setBackgroundDrawable(requireActivity().getDrawable(R.drawable.add_email_conirmation_logo))
            binding.imgBannerAddPeopleEmailConf.scaleType=ImageView.ScaleType.FIT_XY
        }else
        {
            binding.constraintsEmailConfirm.setBackgroundResource(0)
            rightMarginTopLayout=0
            binding.ivOutsideCloseAddEmailConfirmation.visibility=View.GONE
            binding.imgCancelAddPeopleEmailConf.visibility=View.VISIBLE
            binding.imgBannerAddPeopleEmailConf.scaleType=ImageView.ScaleType.FIT_XY
            binding.imgBannerAddPeopleEmailConf.setBackgroundDrawable(requireActivity().getDrawable(R.drawable.add_people))
        }
        val layoutParams = binding.constraintsEmailConfirm.layoutParams as LinearLayout.LayoutParams
        //  val newLayoutParams = toolbar.getLayoutParams()
        layoutParams.topMargin = 0
        layoutParams.leftMargin = 0
        layoutParams.rightMargin = rightMarginTopLayout
        binding.constraintsEmailConfirm.setLayoutParams(layoutParams)
    }
}