package com.yapi.views.signup_code

data class VerifyOTPResponse(
<<<<<<< HEAD
    val `data`: LoginUserData,
=======
    val `data`: Data,
>>>>>>> origin/master
    val message: String,
    val status: Int,
    val token: String
)

<<<<<<< HEAD
data class LoginUserData(
=======
data class Data(
>>>>>>> origin/master
    val __v: Int,
    val _id: String,
    val about: String,
    val blocked: Boolean,
    val country_code: String,
    val createdAt: String,
    val deactivated: Boolean,
    val deleted: Boolean,
    val email: String,
    val email_otp: String,
    val email_otp_verified: Boolean,
    val mobile_number: Any,
    val mobile_otp: String,
    val mobile_otp_verified: Boolean,
    val name: String,
    val profile_pic: String,
    val profile_pic_url: String,
    val updatedAt: String,
<<<<<<< HEAD
    val user_name: String,
    var profile_created: Boolean?=false,
=======
    val user_name: String
>>>>>>> origin/master
)