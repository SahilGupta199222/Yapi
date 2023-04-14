package com.yapi.common

import com.google.gson.JsonObject
import com.yapi.views.sign_in.SignInResponse
import com.yapi.views.signup_code.VerifyOTPResponse
import retrofit2.Response
import retrofit2.http.*

interface RetrofitAPI {
    @GET("getCategory")
    suspend fun getCategory(
        @Header("Authorization") token: String,
        @Query("lat") lat: String,
        @Query("long") lng: String,
        @Query("offset") offset: String,
        @Query("limit") limit: String,
        @Query("search") search: String,
    ): Response<GetCategorryResponse>

    @POST(WebAPIKeys.LOGIN_URL)
    suspend fun loginAPI(@Body emailData:JsonObject): Response<SignInResponse>

    @POST(WebAPIKeys.VERIFY_OTP_URL)
    suspend fun verifyOTPAPI(@Body emailData:JsonObject)
    : Response<VerifyOTPResponse>
}