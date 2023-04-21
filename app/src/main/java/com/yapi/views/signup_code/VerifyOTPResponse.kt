package com.yapi.views.signup_code

data class VerifyOTPResponse(
    val `data`: Data,
    val message: String,
    val status: Int,
    val token: String
)

data class Data(
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
    val user_name: String,
    var profile_created: Boolean?=false,
)