package com.yapi.common

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("getCategory")
   suspend fun getCategory(@Header("Authorization") token:String,
                               @Query("lat") lat:String,
                           @Query("long") lng:String,
                           @Query("offset") offset:String,
                           @Query("limit") limit:String,
                           @Query("search") search:String,
    ):Response<GetCategorryResponse>
}