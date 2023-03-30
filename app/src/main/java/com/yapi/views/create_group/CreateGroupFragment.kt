package com.yapi.views.create_group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginRight
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.databinding.FragmentCreateGroupBinding
import com.yapi.views.add_people.AddPeopleFragment

class CreateGroupFragment : DialogFragment() {
    private lateinit var binding: FragmentCreateGroupBinding
    val viewModel: ViewModelCreateGroup by viewModels()

   /* init {
        if(requireActivity().getResources().getBoolean(R.bool.isTab)) {
            System.out.println("phone========tablet");
        }
    }*/

companion object {
    fun newInstanceCreateGroup(title: String): CreateGroupFragment {
        val args = Bundle()
        args.putString("11", title)
        val fragment = CreateGroupFragment()
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

  /*  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Implement Dialog-specific functionality here
        if(requireActivity().getResources().getBoolean(R.bool.isTab)) {
            return super.onCreateDialog(savedInstanceState)
        }else
        {
            return null!!
        }
    }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateGroupBinding.inflate(LayoutInflater.from(context))
        //inflater.context.setTheme(R.style.FullScreenDialog)
        binding.vModel = viewModel
        addObserverForOpenAddPeople()
        dialogDismissMethod()
        return binding.root
    }

    private fun addObserverForOpenAddPeople() {
        viewModel.addPeopleScreenOpenData.observe(requireActivity(), Observer {
            var data =it as Boolean
            if(data){
                AddPeopleFragment.newInstanceAddPeople("").showNow(requireActivity().supportFragmentManager," SimpleDialog.TAG")
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            var rightMarginTopLayout=0
            if(checkDeviceType())
            {
                rightMarginTopLayout=requireActivity().resources.getDimension(com.intuit.sdp.R.dimen._18sdp).toInt()
                ivOutsideCloseGroup.visibility=View.VISIBLE
                imgCancelCreateGroup.visibility=View.GONE
                layoutCreateGroup.setBackgroundResource(R.drawable.et_drawable)

            }else
            {
                layoutCreateGroup.setBackgroundResource(0)
                rightMarginTopLayout=0
                ivOutsideCloseGroup.visibility=View.GONE
                imgCancelCreateGroup.visibility=View.VISIBLE
            }

           // val ll = LinearLayout(this)
            //ll.setOrientation(LinearLayout.VERTICAL)

//var layoutParams=binding.layoutCreateGroup.layoutParams

            val layoutParams = binding.layoutCreateGroup.layoutParams as LinearLayout.LayoutParams
          //  val newLayoutParams = toolbar.getLayoutParams()
            layoutParams.topMargin = 0
            layoutParams.leftMargin = 0
            layoutParams.rightMargin = rightMarginTopLayout
            binding.layoutCreateGroup.setLayoutParams(layoutParams)
        }

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