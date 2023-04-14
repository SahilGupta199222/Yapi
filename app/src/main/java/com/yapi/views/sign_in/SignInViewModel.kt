package com.yapi.views.sign_in

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.gson.JsonObject
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(val repository: Repository)  : ViewModel() {

    var emailFieldValue = ObservableField("")
    var passwordFieldValue = ObservableField("")
    var emailCorrectValue = ObservableBoolean(false)

    var errorData=MutableLiveData<SignInErrorData>()

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSignIn -> {
                if (checkValidation()) {
                    if(view.findNavController().currentDestination?.id==R.id.signInFragment) {


                      //  loginAPIMethod(view)
                        var bundle= Bundle()
                        bundle.putString("email",emailFieldValue.get())
                        view.findNavController().navigate(R.id.action_signInFragment_to_signUpCodeFragment,bundle)


                    }
                }
            }
            R.id.txtSignIn -> {
                if(view.findNavController().currentDestination?.id==R.id.signInFragment) {
                    view.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment2)
             3   }
            }
            R.id.linearTopSignIn, R.id.constraintsTopSignIN -> {
                //for hide keyboard
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    fun loginAPIMethod(view:View)
    {
        var jsonObject=JsonObject()
        jsonObject.addProperty("email",emailFieldValue.get().toString().trim())
        repository.makeCall(true,
            requestProcessor = object : ApiProcessor<Response<SignInResponse>> {
                override fun onSuccess(success: Response<SignInResponse>) {
                    Log.e("Resposne_Dataaaa===", success.body().toString())

                    var bundle= Bundle()
                    bundle.putString("email",emailFieldValue.get())
                    view.findNavController().navigate(R.id.action_signInFragment_to_signUpCodeFragment,bundle)
                }

                override fun onError(message: String) {
                    MainActivity.activity!!.get()!!.showMessage(message)
                }

                override suspend fun sendRequest(retrofitApi: RetrofitAPI): Response<SignInResponse> {
                    return retrofitApi.loginAPI(jsonObject)
                }
            })
    }

    fun onLongg(view:View):Boolean{
        when (view.id) {
            R.id.btnSignIn -> {
                    if(view.findNavController().currentDestination?.id==R.id.signInFragment) {
//                        view.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment2)
//                        view.findNavController().navigate(R.id.action_signInFragment_to_chipSetDemoFragment)
                    }
                }
            }
        return  true
    }

    private fun checkValidation(): Boolean {
        if (emailFieldValue.get().toString().isEmpty()) {
          //  showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_email))
            errorData.value=SignInErrorData(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_email))
            return false
        } else if (!(isValidEmail(emailFieldValue.get().toString()))) {
          //  showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_valid_email))
            errorData.value=SignInErrorData(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_valid_email))
            return false
        } /*else if (passwordFieldValue.get().toString().isEmpty()) {
            showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_password))
            return false
        } */else {
            errorData.value=SignInErrorData("")
            return true
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")
     /*   if (s.length == 0) {
             emailCorrectValue.set(false)
        } else if (emailFieldValue.get().toString().trim().length>0 && isValidEmail(emailFieldValue.get().toString())) {
            emailCorrectValue.set(true)
        } else {
            emailCorrectValue.set(false)
        }*/
    }

    fun AfterTextChanged(s: Editable?) {
     if (emailFieldValue.get().toString().trim().length>0 && isValidEmail(emailFieldValue.get().toString())) {
            emailCorrectValue.set(true)
         errorData.value=SignInErrorData("")
        } else {
            emailCorrectValue.set(false)
        }
    }
}