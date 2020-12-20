package com.example.android.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "https://mars.udacity.com/"

/**
 * retrofit need 2 things in order to work :
 * 1- Base Url
 * 2- Converter factory which is here Scalars...
 */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

/**
 * the app only needs one Retrofit service instance, lazily initialize the Retrofit service there
 */
object MarsApi {
    //use lazy so that it will only created when it is needed.
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}