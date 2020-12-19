package com.example.android.marsrealestate.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "https://mars.udacity.com/"
/**
 * retrofit need 2 things in order to work :
 * 1- Base Url
 * 2- Converter factory which is here Scalars...
 */
private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
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