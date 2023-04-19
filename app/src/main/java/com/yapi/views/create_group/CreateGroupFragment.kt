package com.yapi.views.create_group

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yapi.R
import com.yapi.common.changeBackgroundForEditError
import com.yapi.common.checkDeviceType
import com.yapi.common.hideKeyboard
import com.yapi.databinding.FragmentCreateGroupBinding
import com.yapi.pref.PreferenceFile
import com.yapi.views.add_people.AddPeopleFragment
import com.yapi.views.sign_in.SignInErrorData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateGroupFragment : DialogFragment() {
    private lateinit var binding: FragmentCreateGroupBinding
    val viewModel: ViewModelCreateGroup by viewModels()

    @Inject
    lateinit var preferenceFile: PreferenceFile
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
        if (checkDeviceType()) {
            System.out.println("phone========tablet")
            setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenDialog)
            //setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_NoTitleBar);
        //}

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
        hideKeyboardObserver()
        return binding.root
    }

    private fun hideKeyboardObserver() {

        viewModel.hideKeyboardData.observe(requireActivity(), Observer {
            var data=it as Boolean
            if(data)
            {
                requireActivity().hideKeyboard()
            }
        })
    }

    private fun addObserverForOpenAddPeople() {
        viewModel.addPeopleScreenOpenData.observe(requireActivity(), Observer {
            var data = it as Boolean
            if (data) {
                AddPeopleFragment.newInstanceAddPeople("")
                    .showNow(requireActivity().supportFragmentManager, " SimpleDialog.TAG")
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        showErrorUIObserver()
        binding.apply {
            var rightMarginTopLayout = 0
            if (checkDeviceType()) {
                rightMarginTopLayout =
                    requireActivity().resources.getDimension(com.intuit.sdp.R.dimen._18sdp).toInt()
                ivOutsideCloseGroup.visibility = View.VISIBLE
                imgCancelCreateGroup.visibility = View.GONE
                layoutCreateGroup.setBackgroundResource(R.drawable.et_drawable)
            } else {
                layoutCreateGroup.setBackgroundResource(0)
                rightMarginTopLayout = 0
                ivOutsideCloseGroup.visibility = View.GONE
                imgCancelCreateGroup.visibility = View.VISIBLE
            }

            // val ll = LinearLayout(this)
            //ll.setOrientation(LinearLayout.VERTICAL)

            //var layoutParams=binding.layoutCreateGroup.layoutParams

            val layoutParams = binding.layoutCreateGroup.layoutParams as LinearLayout.LayoutParams
            //  val newLayoutParams = toolbar.getLayoutParams()
            layoutParams.topMargin = 0
            layoutParams.leftMargin = 0
            layoutParams.rightMargin = rightMarginTopLayout
            binding.layoutCreateGroup.layoutParams = layoutParams
        }
    }

    fun dialogDismissMethod() {
        viewModel.dismissDialogData.observe(requireActivity(), Observer {
            var data = it as Boolean
            if (data) {
                dismiss()
            }
        })
    }

    fun showErrorUIObserver()
    {
        viewModel.errorData.observe(requireActivity(), Observer {
            var data=it as SignInErrorData

            var editText:AppCompatEditText?=null
            var errorText: AppCompatTextView?=null
            if(data.fieldId==1)
            {
                editText=binding.etGroupNameCreateGroup
                errorText=binding.txtErrorEmailSignup
            }else
                if(data.fieldId==2)
                {
                    editText=binding.etGroupDescriptionCreateGroup
                    errorText=binding.txtErrorDescription
                }else{
                    binding.txtErrorEmailSignup!!.setText("")
                    changeBackgroundForEditError(binding.etGroupNameCreateGroup!!,requireActivity().resources.getColor(
                        R.color.white),
                        requireActivity().resources.getColor(R.color.liteGrey))

                    binding.txtErrorDescription!!.setText("")
                    changeBackgroundForEditError(binding.etGroupDescriptionCreateGroup!!,requireActivity().resources.getColor(
                        R.color.white),
                        requireActivity().resources.getColor(R.color.liteGrey))
                }

            if(data!=null && data.message.isNotEmpty())
            {
                errorText!!.setText(data.message)

                changeBackgroundForEditError(editText!!, ContextCompat.getColor(requireContext(),R.color.error_box_color),
                    ContextCompat.getColor(requireContext(),R.color.error_border_color))
            }else {
                if (data.fieldId != 0) {
                    errorText!!.setText("")


                    changeBackgroundForEditError(editText!!, ContextCompat.getColor(requireContext(),R.color.liteGrey),
                        ContextCompat.getColor(requireContext(),R.color.white))
                }
            }
        })
    }
}