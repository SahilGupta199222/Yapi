package com.yapi.views.edit_profile

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.yapi.BuildConfig
import com.yapi.R
import com.yapi.common.*
import com.yapi.databinding.FragmentEditProfileBinding
import com.yapi.pref.PreferenceFile
import com.yapi.views.profile.ProfileData
import com.yapi.views.sign_in.SignInErrorData
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class EditProfileFragment : DialogFragment(), View.OnClickListener {

    @Inject
    lateinit var preferenceFile: PreferenceFile

    companion object {
        fun newInstanceEditProfileScreen(title: String,profileBundle:ProfileData): EditProfileFragment {
            val args = Bundle()
            args.putString("11", title)
            args.putSerializable("profile_data",profileBundle)
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
       // window.setLayout(second_frame_width.toInt(),second_frame_height.toInt())
        return dialog
    }

    private lateinit var binding: FragmentEditProfileBinding
    private val viewModel: ViewModelEditProfile by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEditProfileBinding.inflate(LayoutInflater.from(requireContext()))
        binding.model = viewModel

        if(Constants.API_CALL_DEMO){
       var profileData=  requireArguments().getSerializable("profile_data") as ProfileData
        if(profileData!=null) {
            viewModel.nameValue.set(profileData.name)
            viewModel.userNameValue.set(profileData.user_name)
            viewModel.emailAddressValue.set(profileData.email)
            viewModel.countryCodeValue.set(profileData.country_code.toString())
            viewModel.aboutValue.set(profileData.about.toString())
            var phoneNumber=  addSpaceBetweenPhoneMethod(profileData.mobile_number.toString())
            viewModel.phoneNumberValue.set(phoneNumber)
        }}else
        {
            viewModel.nameValue.set("Testing")
            viewModel.userNameValue.set("Demo")
            viewModel.emailAddressValue.set("Testing1@gmail.com")
            viewModel.aboutValue.set("About Data")
            var phoneNumber=  addSpaceBetweenPhoneMethod("0123456789")
            viewModel.phoneNumberValue.set(phoneNumber)
        }
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
        showErrorUIObserver()
        phoneErrorObserver()
        dismissDialogMethod()
        setTopLayoutMethod()
        scrollEditTextMethod()

        binding.ivDrpArrow.setOnClickListener(this)
        binding.layoutUploadImageEditProfile.setOnClickListener(this)

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

                if (binding.etNumberEditProfile.text.toString().length>0) {
                    binding.txtErrorPhone.setText("")
                    changeBackgroundForError(binding.layoutNumberProfileEdit!!, requireActivity().resources.getColor(
                        R.color.white),
                        requireActivity().resources.getColor(R.color.liteGrey))
                }

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
                binding.countryCodePickerEditProfile.showCountryCodePickerDialog()
                //  if(!(binding.countryCodePickerEditProfile.isShown)) {
                //showToastMessage("Hello")
                // binding.countryCodePickerEditProfile.
                //  }
            }
            R.id.layoutUploadImageEditProfile->{
                openCameraIntent()
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

    //For Scroll Yourself text
    fun scrollEditTextMethod()
    {
        binding.etAboutYourSelfEditProfile.setOnTouchListener(object : View.OnTouchListener {
          override  fun onTouch(v: View, event: MotionEvent): Boolean {
                if (binding.etAboutYourSelfEditProfile.hasFocus()) {
                    v.parent.requestDisallowInterceptTouchEvent(true)
                    when (event.getAction() and MotionEvent.ACTION_MASK) {
                        MotionEvent.ACTION_SCROLL -> {
                            v.parent.requestDisallowInterceptTouchEvent(false)
                            return true
                        }
                    }
                }
                return false
            }
        })
    }

    fun phoneErrorObserver()
    {
        viewModel.phoneErrorData.observe(requireActivity(), Observer {
            var data=it as SignInErrorData
            var editText: ConstraintLayout?=null
            var errorText: AppCompatTextView?=null
            if(data!=null && data.message!="")
            {
                editText=binding.layoutNumberProfileEdit
                errorText=binding.txtErrorPhone
                errorText!!.setText(data.message)
                changeBackgroundForError(editText!!,requireActivity().resources.getColor(
                    R.color.error_box_color),
                    requireActivity().resources.getColor(R.color.error_border_color))
            }else
            {
                editText=binding.layoutNumberProfileEdit
                errorText=binding.txtErrorPhone
                errorText!!.setText("")
                changeBackgroundForError(editText!!,requireActivity().resources.getColor(
                    R.color.white),
                    requireActivity().resources.getColor(R.color.liteGrey))
            }
        })
    }

    fun showErrorUIObserver()
    {
        viewModel.errorData.observe(requireActivity(), Observer {
            var data=it as SignInErrorData

            if(data.fieldId==1)
            {
                if(data.message!="")
                {
                    setSelectedDataMethod(binding.txtErrorName!!,binding.etNameEditProfile!!,data.message)
                }else
                {
                    setDefaultDataMethod(binding.txtErrorName!!,binding.etNameEditProfile!!)
                }
            }else
                if(data.fieldId==2)
                {
                    if(data.message!="")
                    {
                        setSelectedDataMethod(binding.txtErrorUserName!!,binding.etUserNameEditProfile!!,data.message)
                    }else
                    {
                        setDefaultDataMethod(binding.txtErrorUserName!!,binding.etUserNameEditProfile!!)
                    }
                }else
                    if(data.fieldId==3)
                    {
                        if(data.message!="")
                        {
                            setSelectedDataMethod(binding.txtErrorEmail!!,binding.etEmailEditProfile!!,data.message)
                        }else
                        {
                            setDefaultDataMethod(binding.txtErrorEmail!!,binding.etEmailEditProfile!!)
                        }
                    }else
                        if(data.fieldId==4)
                        {
                          /*  editText=binding.layoutNumberProfileEdit
                            errorText=binding.txtErrorPhone*/

                        }else
                            if(data.fieldId==5)
                            {
                                if(data.message!="")
                                {
                                    setSelectedDataMethod(binding.txtErrorAbout!!,binding.etAboutYourSelfEditProfile!!,data.message)
                                }else
                                {
                                    setDefaultDataMethod(binding.txtErrorAbout!!,binding.etAboutYourSelfEditProfile!!)
                                }

                            }

      /*      if(data!=null && data.message.isNotEmpty())
            {
                errorText!!.setText(data.message)
                changeBackgroundForEditError(editText!!,requireActivity().resources.getColor(
                    R.color.error_box_color),
                    requireActivity().resources.getColor(R.color.error_border_color))
            }else {
                if (data.fieldId != 0) {
                    errorText!!.setText("")
                    changeBackgroundForEditError(editText!!, requireActivity().resources.getColor(
                        R.color.white),
                        requireActivity().resources.getColor(R.color.liteGrey))
                }


            }*/
        })
    }

    fun setSelectedDataMethod(txtErrorName:AppCompatTextView,etNameEditProfile:AppCompatEditText,message:String)
    {
        txtErrorName.setText(message)
        changeBackgroundForEditError(etNameEditProfile!!,requireActivity().resources.getColor(
            R.color.error_box_color),
            requireActivity().resources.getColor(R.color.error_border_color))
    }

    fun setDefaultDataMethod(txtErrorName:AppCompatTextView,etNameEditProfile:AppCompatEditText)
    {
        txtErrorName.setText("")
        changeBackgroundForEditError(etNameEditProfile,requireActivity().resources.getColor(
            R.color.white),
            requireActivity().resources.getColor(R.color.liteGrey))
    }

    private val REQUEST_CAPTURE_IMAGE = 200
    var photoFile: File? = null
    private fun openCameraIntent() {
        val pictureIntent = Intent(
            MediaStore.ACTION_IMAGE_CAPTURE)
        if (pictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            //Create a file to store the image

            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
            }
            if (photoFile != null) {
                val photoURI: Uri =
                    FileProvider.getUriForFile(requireActivity(), "${BuildConfig.APPLICATION_ID}.fileprovider", photoFile!!)
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    photoURI)
                startActivityForResult(pictureIntent,
                    REQUEST_CAPTURE_IMAGE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if(requestCode==REQUEST_CAPTURE_IMAGE)
    {
        //binding.imgGalleryUploadEditProfile.set
        Glide.with(requireActivity())
            .load(photoFile)
           // .override(300, 200)
            .into(binding.imgPicImageCreateGroup)
    }
    }

    var imagePath: String? = null
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss",
            Locale.getDefault()).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir: File = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val image = File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        )
        imagePath = image.absolutePath
        return image
    }
}