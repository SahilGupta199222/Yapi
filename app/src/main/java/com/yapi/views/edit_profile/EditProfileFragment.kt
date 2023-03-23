package com.yapi.views.edit_profile

import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yapi.R
import com.yapi.common.showToastMessage
import com.yapi.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment(), View.OnClickListener {
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

    private fun init() {
        binding.ivDrpArrow.setOnClickListener(this)

        setPhoneMethod(binding.countryCodePickerEditProfile.selectedCountryCodeWithPlus)
        binding.apply {
            countryCodePickerEditProfile.setOnCountryChangeListener {
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
      /*  viewModel.countryCodeValue.set(selectedCountryCode.toString())
        binding.etNumberEditProfile.setSelection(binding.etNumberEditProfile.text.toString()
            .trim().length)
        var profilePic = binding.countryCodePickerEditProfile.imageViewFlag
        val imageBitmap = BitmapFactory.decodeResource(resources,
            binding.countryCodePickerEditProfile.selectedCountryFlagResourceId)
        val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, imageBitmap)
        var radiusValue = requireActivity().resources.getDimension(com.intuit.sdp.R.dimen._5sdp)
        roundedBitmapDrawable.cornerRadius = radiusValue
        roundedBitmapDrawable.setAntiAlias(true)
        profilePic.setImageDrawable(roundedBitmapDrawable)*/
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
}