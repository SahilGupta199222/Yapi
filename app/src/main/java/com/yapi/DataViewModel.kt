package com.yapi

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yapi.common.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DataViewModel @Inject constructor(
    @Named("abed") private var nameValue: String,
    private var repository: Repository,
) : ViewModel() {

    fun getCategoryMethod() {
        repository.makeCall(false,
            requestProcessor = object : ApiProcessor<Response<GetCategorryResponse>> {
                override fun onSuccess(success: Response<GetCategorryResponse>) {
                    Log.e("Resposne_Dataaaa===", success.body().toString())
                }

                override fun onError(message: String) {
                    MainActivity.activity!!.get()!!.showMessage(message)
                }

                override suspend fun sendRequest(retrofitApi: RetrofitAPI): Response<GetCategorryResponse> {
                     return retrofitApi.getCategory("30.8987", "76.7179", "1", "20", "")
                }
            })
    }
}