package com.yapi.views.edit_profile

import android.text.Editable
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.common.hideKeyboard
import com.yapi.common.isValidEmail
import com.yapi.common.showToastMessage
import com.yapi.views.sign_in.SignInErrorData

class ViewModelEditProfile:ViewModel() {
    var countryCodeValue=ObservableField("")
    var phoneNumberValue=ObservableField("")
    var dismissDialogData=MutableLiveData<Boolean>()

    var checkTabValid:Boolean?=false
    var constraintsMarginEnd:Int?=0

    var nameValue=ObservableField<String>("")
    var userNameValue=ObservableField<String>("")
    var emailAddressValue=ObservableField<String>("")
    var aboutValue=ObservableField<String>("")

    var errorData=MutableLiveData<SignInErrorData>()
    var phoneErrorData=MutableLiveData<SignInErrorData>()

    fun onClick(view: View) {
        when (view.id) {
        R.id.layoutEditProfile ,R.id.layoutScrollViewEditProfile->{
            //for hide keyboard
            MainActivity.activity!!.get()!!.hideKeyboard()
        }
            R.id.imgCancelEditProfile,R.id.ivOutsideCloseAddPeople->{
                if(checkDeviceType())
                {
                    dismissDialogData.value=true
                }else
                {
                    if(view.findNavController().currentDestination?.id == R.id.editProfileFragment){
                        view.findNavController().popBackStack()
                    }
                }
            }
            R.id.btnDoneEditProfile->{
                if(checkDeviceType())
                {
                    dismissDialogData.value=true
                }else
                {
                    if(view.findNavController().currentDestination?.id == R.id.editProfileFragment){
                        if(checkValidation())
                        {
                            view.findNavController().popBackStack()
                        }
                    }
                }
            }
    }
    }




    fun AfterTextChanged(s: Editable) {

     /*   if(phoneNumberValue.get().toString().length==4 || phoneNumberValue.get().toString().length==10)
        {
            phoneNumberValue.set(phoneNumberValue.get().toString()+" ")
        }*/
        /*if (emailFieldValue.get().toString().trim().length>0 && isValidEmail(emailFieldValue.get().toString())) {
            emailCorrectValue.set(true)
        } else {
            emailCorrectValue.set(false)
        }*/
    }

    fun checkValidation():Boolean
    {
        if(nameValue.get().toString().trim().isEmpty())
        {
            errorData.value= SignInErrorData(MainActivity.activity!!.get()!!.getString(R.string.please_enter_name_text),1)
           // showToastMessage(MainActivity.activity!!.get()!!.getString(R.string.please_enter_name_text))
            return false
        }else
            if(userNameValue.get().toString().trim().isEmpty())
            {
                errorData.value= SignInErrorData(MainActivity.activity!!.get()!!.getString(R.string.please_enter_user_name_text),2)

             //  showToastMessage(MainActivity.activity!!.get()!!.getString(R.string.please_enter_user_name_text))
                return false
            }else
                if(emailAddressValue.get().toString().trim().isEmpty())
                {
                    errorData.value= SignInErrorData(MainActivity.activity!!.get()!!.getString(R.string.please_enter_email_text),3)

               //     showToastMessage(MainActivity.activity!!.get()!!.getString(R.string.please_enter_email_text))
                    return false
                }else
                    if(!(isValidEmail(emailAddressValue.get().toString().trim())))
                    {
                        errorData.value= SignInErrorData(MainActivity.activity!!.get()!!.getString(R.string.please_enter_valid_email_text),3)

                        // showToastMessage(MainActivity.activity!!.get()!!.getString(R.string.please_enter_valid_email_text))
                        return false
                    }else
                        if(phoneNumberValue.get().toString().trim().length<10)
                        {
                            phoneErrorData.value= SignInErrorData(MainActivity.activity!!.get()!!.getString(R.string.please_enter_phone_text),4)

                         //   showToastMessage(MainActivity.activity!!.get()!!.getString(R.string.please_enter_phone_text))
                            return false
                        }
                        else
                            if(aboutValue.get().toString().trim().isEmpty())
                            {
                                errorData.value= SignInErrorData(MainActivity.activity!!.get()!!.getString(R.string.please_enter_yourself_text),5)

                              //  showToastMessage(MainActivity.activity!!.get()!!.getString(R.string.please_enter_yourself_text))
                                return false
                            }
        return true
    }

    fun nameTextChange(nameText: CharSequence?, start: Int, before: Int, count: Int)
    {
        if(nameText.toString().trim().length==1)
        {
            errorData.value=SignInErrorData("",1)
        }
    }

    fun userNameTextChange(userNameText: CharSequence?, start: Int, before: Int, count: Int)
    {
        if(userNameText.toString().trim().length==1)
        {
            errorData.value=SignInErrorData("",2)
        }
    }

    fun emailTextChange(nameEmail: CharSequence?, start: Int, before: Int, count: Int)
    {
        if(isValidEmail(nameEmail.toString().trim()))
        {
            errorData.value=SignInErrorData("",3)
        }
    }

    fun aboutTextChange(aboutText: CharSequence?, start: Int, before: Int, count: Int)
    {
        if(aboutText!!.trim().length==1)
        {
            errorData.value=SignInErrorData("",5)
        }
    }
}