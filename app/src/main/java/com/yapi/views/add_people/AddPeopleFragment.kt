package com.yapi.views.add_people

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.databinding.FragmentAddPeopleBinding
import com.yapi.views.add_people_email.AddPeopleEmailFragment
import com.yapi.views.create_group.CreateGroupFragment

class AddPeopleFragment : DialogFragment() {
    private lateinit var binding: FragmentAddPeopleBinding
    private val viewModel: ViewModelAddPeople by viewModels()

    companion object {
        fun newInstanceAddPeople(title: String): AddPeopleFragment {
            val args = Bundle()
            args.putString("11", title)
            val fragment = AddPeopleFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(requireActivity().getResources().getBoolean(R.bool.isTab)) {
            System.out.println("phone========tablet");
            setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenDialog)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddPeopleBinding.inflate(LayoutInflater.from(requireActivity()))
            binding.vModel = viewModel
        addObserverForOpenAddPeople()
        dialogDismissMethod()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {

        }
        setTopLayoutMethod()
    }

    fun setTopLayoutMethod()
    {
        var rightMarginTopLayout=0
        if(checkDeviceType())
        {
            rightMarginTopLayout=requireActivity().resources.getDimension(com.intuit.sdp.R.dimen._18sdp).toInt()
            binding.ivOutsideCloseAddPeople.visibility=View.VISIBLE
            binding.imgCancelAddPeople.visibility=View.GONE
            binding.constraintsAddPeople.setBackgroundResource(R.drawable.et_drawable)
        }else
        {
            binding.constraintsAddPeople.setBackgroundResource(0)
            rightMarginTopLayout=0
            binding.ivOutsideCloseAddPeople.visibility=View.GONE
            binding.imgCancelAddPeople.visibility=View.VISIBLE
        }

        val layoutParams = binding.constraintsAddPeople.layoutParams as LinearLayout.LayoutParams
        //  val newLayoutParams = toolbar.getLayoutParams()
        layoutParams.topMargin = 0
        layoutParams.leftMargin = 0
        layoutParams.rightMargin = rightMarginTopLayout
        binding.constraintsAddPeople.setLayoutParams(layoutParams)
    }

    private fun addObserverForOpenAddPeople() {
        viewModel.addPeopleEmailScreenOpenData.observe(requireActivity(), Observer {
            var data =it as Boolean
            if(data){
                AddPeopleEmailFragment.newInstanceAddPeopleEmail("").showNow(requireActivity().supportFragmentManager," SimpleDialog.TAG")
            }
        })
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
}