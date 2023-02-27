package com.yapi.common

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            this.addInterceptor(Interceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNjNjZTIzMTFhNjdjNzA4NTc0MzlmNTQzIiwiaWF0IjoxNjc1MDYxMjUxfQ.-XFkdhmagEV-4V31HWuQ39nK2rsfmVaNUceoA5Zlyrw")
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(25,TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().client(okHttpClient).
        addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    }

    @Provides
    @Singleton
    fun provideImageApiService(builder: Retrofit.Builder): RetrofitAPI {
        return (builder.baseUrl(WebAPIKeys.BASEURL).build()).create(RetrofitAPI::class.java)
    }

    @Provides
    @Singleton
    @Named("Sample")
    fun provideImageApiService1(builder: Retrofit.Builder): RetrofitAPI {
        return (builder.baseUrl(WebAPIKeys.BASEURL).build()).create(RetrofitAPI::class.java)
    }
}

//http://192.168.1.87:3000/a uth/getCategory?lat=30.8987&long=76.7179&offset=1&limit=10&search=