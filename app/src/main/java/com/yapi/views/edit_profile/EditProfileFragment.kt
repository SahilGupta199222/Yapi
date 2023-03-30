package com.yapi.views.edit_profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rilixtech.widget.countrycodepicker.CountryUtils
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.databinding.FragmentEditProfileBinding
import com.yapi.views.profile.ProfileFragment


class EditProfileFragment : DialogFragment(), View.OnClickListener {

    companion object {
        fun newInstanceEditProfileScreen(title: String): EditProfileFragment {
            val args = Bundle()
            args.putString("11", title)
            val fragment = EditProfileFragment()
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

    private lateinit var binding: FragmentEditProfileBinding
    private val viewModel: ViewModelEditProfile by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEditProfileBinding.inflate(LayoutInflater.from(requireContext()))
        binding.model = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun setTopLayoutMethod()
    {
        var rightMarginTopLayout=0
        if(checkDeviceType())
        {
            viewModel.checkTabValid=true
            rightMarginTopLayout=requireActivity().resources.getDimension(com.intuit.sdp.R.dimen._18sdp).toInt()
            binding.layoutEditProfile.setBackgroundResource(R.drawable.et_drawable)
        }else
        {
            viewModel.checkTabValid=false
            rightMarginTopLayout=0
            binding.layoutEditProfile.setBackgroundResource(0)
        }

        val layoutParams = binding.layoutEditProfile.layoutParams as LinearLayout.LayoutParams
        //  val newLayoutParams = toolbar.getLayoutParams()
        layoutParams.topMargin = 0
        layoutParams.leftMargin = 0
        layoutParams.rightMargin = rightMarginTopLayout
        binding.layoutEditProfile.setLayoutParams(layoutParams)
    }

    private fun init() {
        dismissDialogMethod()
        setTopLayoutMethod()

        binding.ivDrpArrow.setOnClickListener(this)

        setPhoneMethod(binding.countryCodePickerEditProfile.selectedCountryCodeWithPlus)
        binding.apply {
            countryCodePickerEditProfile.setOnCountryChangeListener {
                Log.e("fefefewefwddf===", countryCodePickerEditProfile.selectedCountryCodeWithPlus)
                var selected_country_code =
                    countryCodePickerEditProfile.selectedCountryCodeWithPlus
                setPhoneMethod(selected_country_code!!)
            }
        }

        binding.etNumberEditProfile.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                //You can identify which key pressed by checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //binding.etNumberEditProfile.inputType=InputType.TYPE_CLASS_TEXT
                    //this is for backspace
                    if (binding.etNumberEditProfile.text.toString().length == 10 || binding.etNumberEditProfile.text.toString().length == 5) {
                        binding.etNumberEditProfile.setText(binding.etNumberEditProfile.text.toString()
                            .trim())
                    }
                } else {
                    //binding.etNumberEditProfile.inputType=InputType.TYPE_CLASS_NUMBER
                }
                return false
            }
        })

        binding.etNumberEditProfile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etNumberEditProfile.text.toString().length == 4) {
                    var first = binding.etNumberEditProfile.text.toString().substring(0, 3)
                    var last = binding.etNumberEditProfile.text.toString().substring(3, 4)

                    binding.etNumberEditProfile.setText(first + " " + last)
                    binding.etNumberEditProfile.setSelection(binding.etNumberEditProfile.text.toString().length)
                } else
                    if (binding.etNumberEditProfile.text.toString().length == 9) {
                        var first = binding.etNumberEditProfile.text.toString().substring(0, 8)
                        var last = binding.etNumberEditProfile.text.toString().substring(8, 9)

                        binding.etNumberEditProfile.setText(first + " " + last)


                    }
                binding.etNumberEditProfile.setSelection(binding.etNumberEditProfile.text.toString().length)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    fun setPhoneMethod(selectedCountryCode: String) {
        viewModel.countryCodeValue.set(selectedCountryCode.toString())
        binding.etNumberEditProfile.setSelection(binding.etNumberEditProfile.text.toString()
            .trim().length)

        //var profilePic = binding.countryCodePickerEditProfile.mImvFlag
        viewModel.countryCodeValue.set(selectedCountryCode.toString())
        binding.etNumberEditProfile.setSelection(binding.etNumberEditProfile.text.toString()
            .trim().length)
        var profilePic = binding.countryCodePickerEditProfile.mImvFlag
        //profilePic.invalidate()
        /* val imageBitmap = BitmapFactory.decodeResource(resources,
             binding.countryCodePickerEditProfile.selectedCountry.name)*/


        // profilePic.setDrawingCacheEnabled(true);
        //val imageBitmap: Bitmap = profilePic.getDrawingCache()


        /*var  resourceId= CountryUtils.getFlagDrawableResId(binding.countryCodePickerEditProfile.selectedCountry)
           val imageBitmap = BitmapFactory.decodeResource(resources, resourceId)

           val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, imageBitmap)
           var radiusValue = requireActivity().resources.getDimension(com.intuit.sdp.R.dimen._3sdp)
           roundedBitmapDrawable.cornerRadius = radiusValue
           roundedBitmapDrawable.setAntiAlias(true)
           profilePic.setImageDrawable(roundedBitmapDrawable)*/

        /* binding.imgProfilePicEditProfile.setImageBitmap(imageBitmap)
         binding.imgProfilePicEditProfile.invalidate()*/
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivDrpArrow -> {
                //  if(!(binding.countryCodePickerEditProfile.isShown)) {
                //showToastMessage("Hello")
                // binding.countryCodePickerEditProfile.
                //  }
            }
        }
    }

    fun dismissDialogMethod() {
        viewModel.dismissDialogData.observe(requireActivity(), Observer {
            var data = it as Boolean
            if (data) {
                dismiss()
            }
        })
    }
}