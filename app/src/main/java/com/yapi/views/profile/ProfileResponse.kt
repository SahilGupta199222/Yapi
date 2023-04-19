package com.yapi.views.profile

data class ProfileResponse(
    val `data`: ProfileData,
    val message: String,
    val status: Int
)

data class ProfileData(
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
    val mobile_number: Long,
    val mobile_otp: String,
    val mobile_otp_verified: Boolean,
    val name: String,
    val profile_pic: String,
    val profile_pic_url: String,
    val updatedAt: String,
    val user_name: String
):java.io.Serializable