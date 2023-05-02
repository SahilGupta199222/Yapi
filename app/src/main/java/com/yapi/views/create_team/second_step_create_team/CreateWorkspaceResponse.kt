package com.yapi.views.create_team.second_step_create_team

data class CreateWorkspaceResponse(
    val `data`: CreateWorkspaceData,
    val message: String,
    val status: Int
)

data class CreateWorkspaceData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val deleted: Boolean,
    val owner_id: String,
    val quick_join: Boolean,
    val role: String,
    val updatedAt: String,
    val workSpace_image: String,
    val working_on: String,
    val workspace_name: String
)