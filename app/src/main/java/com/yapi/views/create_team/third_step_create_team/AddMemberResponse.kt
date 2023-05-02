package com.yapi.views.create_team.third_step_create_team

data class AddMemberResponse(
    val `data`: AddMemberData,
    val message: String,
    val status: Int
)

data class AddMemberData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val deleted: Boolean,
    val invitedEmails: List<InvitedEmail>,
    val ownerDetail: List<OwnerDetail>,
    val owner_id: String,
    val quick_join: Boolean,
    val role: String,
    val updatedAt: String,
    val workSpace_image: String,
    val working_on: String,
    val workspace_name: String
)

data class InvitedEmail(
    val __v: Int,
    val _id: String,
    val account_deactivate: Boolean,
    val account_deleted: Boolean,
    val country_code: String,
    val createdAt: String,
    val email: String,
    val invite_status: String,
    val mobile_number: String,
    val name: String,
    val number_verified: String,
    val profile_pic: String,
    val profile_url: String,
    val region: String,
    val role: String,
    val updatedAt: String,
    val user_name: String,
    val workspace_id: String
)

data class OwnerDetail(
    val __v: Int,
    val _id: String,
    val account_deactivate: Boolean,
    val account_deleted: Boolean,
    val country_code: String,
    val createdAt: String,
    val email: String,
    val invite_status: String,
    val mobile_number: String,
    val name: String,
    val number_verified: String,
    val profile_pic: String,
    val profile_url: String,
    val region: String,
    val role: String,
    val updatedAt: String,
    val user_name: String,
    val workspace_id: String
)