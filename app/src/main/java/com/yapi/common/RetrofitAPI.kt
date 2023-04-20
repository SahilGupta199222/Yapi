package com.yapi.common

import com.google.gson.JsonObject
import com.yapi.views.add_people_email.AddEmailResponse
import com.yapi.views.create_team.second_step_create_team.CreateTeamResponse
import com.yapi.views.edit_profile.EditProfileResponse
import com.yapi.views.profile.ProfileResponse
import com.yapi.views.sign_in.SignInResponse
import com.yapi.views.signup_code.VerifyOTPResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

@Multipart
    @POST(WebAPIKeys.USER_EDIT_PROFILE)
    suspend fun editProfileAPI(
        @Part("name") name:RequestBody,
        @Part("user_name") user_name:RequestBody,
        @Part("email") email:RequestBody,
        @Part("mobile_number") mobile_number:RequestBody,
        @Part("country_code") country_code:RequestBody,
        @Part("about") about:RequestBody,
    ): Response<EditProfileResponse>


    @GET(WebAPIKeys.USER_FETCH_PROFILE+"/{user_id}")
    suspend fun fetchProfileAPI(@Path("user_id") user_id:String)
            : Response<ProfileResponse>


    @Multipart
    @POST(WebAPIKeys.GROUP_CREATE_TEAM)
    suspend fun createTeamAPI(
        @Part("name") name:RequestBody,
        @Part("working") working:RequestBody,
        @Part("description") description:RequestBody,
        @Part("is_private") is_private:RequestBody,
        @Part image: MultipartBody.Part?,
        @Part("quick_join") quick_join:RequestBody,
    ): Response<CreateTeamResponse>


    @POST(WebAPIKeys.ADD_TEAM_MEMBERS)
    suspend fun addTeamMembersAPI(@Body emailData:JsonObject): Response<AddEmailResponse>
}