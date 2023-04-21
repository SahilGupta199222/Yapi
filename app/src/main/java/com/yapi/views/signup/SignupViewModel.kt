package com.yapi.views.signup

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.gson.JsonObject
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.*
import com.yapi.views.sign_in.SignInErrorData
import com.yapi.views.sign_in.SignInResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(val repository: Repository): ViewModel() {

    var emailValueField = ObservableField("")
    var emailCorrectValue = ObservableBoolean(false)
    var errorData=MutableLiveData<SignInErrorData>()
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSignUp -> {
                if(view.findNavController().currentDestination?.id==R.id.signUpFragment2) {
                    if (checkValidation()) {

<<<<<<< HEAD
                        if(Constants.API_CALL_DEMO){
                            signupAPIMethod(view)
                        }else
                        {
                            var bundle = Bundle()
                            bundle.putString("email", emailValueField.get())
                            view.findNavController()
                                .navigate(R.id.action_signUpFragment2_to_signUpCodeFragment, bundle)
                        }
                       // signupAPIMethod(view)
=======
                       // signupAPIMethod(view)
                        var bundle = Bundle()
                        bundle.putString("email", emailValueField.get())
                        view.findNavController()
                            .navigate(R.id.action_signUpFragment2_to_signUpCodeFragment, bundle)
>>>>>>> origin/master
                    }
                }
            }
            R.id.linearTopSignup, R.id.constarintsTopSignup -> {
                MainActivity.activity!!.get()!!.hideKeyboard()
            }
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        /*Log.w("tag", "onTextChanged $s")
        if(s.length==0){
            emailCorrectValue.set(false)
        }else
        {
            emailCorrectValue.set(true)
        }*/
    }

    fun AfterTextChanged(s: Editable?) {
        if (emailValueField.get().toString().trim().length > 0 && isValidEmail(emailValueField.get()
                .toString())
        ) {
            emailCorrectValue.set(true)
            errorData.value= SignInErrorData("",0)
        } else {
            emailCorrectValue.set(false)
        }
    }

    private fun checkValidation(): Boolean {
        if (emailValueField.get().toString().isEmpty()) {
            //  showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_email))
            errorData.value=SignInErrorData(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_email))
            return false
        } else if (!(isValidEmail(emailValueField.get().toString()))) {
            //  showToastMessage(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_valid_email))
            errorData.value=SignInErrorData(MainActivity.activity!!.get()!!.resources.getString(R.string.please_enter_valid_email))
            return false
        } else {
            errorData.value=SignInErrorData("")
            return true
        }
    }

    fun signupAPIMethod(view:View)
    {
        var jsonObject= JsonObject()
        jsonObject.addProperty("email",emailValueField.get().toString().trim())
        repository.makeCall(true,
            requestProcessor = object : ApiProcessor<Response<SignInResponse>> {
                override fun onSuccess(success: Response<SignInResponse>) {
                    Log.e("Resposne_Dataaaa===", success.body().toString())

<<<<<<< HEAD
                    val bundle= Bundle()
                    bundle.putString("email",emailValueField.get())
=======
                    var bundle = Bundle()
                    bundle.putString("email", emailValueField.get())
>>>>>>> origin/master
                    view.findNavController()
                        .navigate(R.id.action_signUpFragment2_to_signUpCodeFragment, bundle)
                }

                override fun onError(message: String) {
                    MainActivity.activity!!.get()!!.showMessage(message)
                }

                override suspend fun sendRequest(retrofitApi: RetrofitAPI): Response<SignInResponse> {
                    return retrofitApi.loginAPI(jsonObject)
                }
            })
    }
}