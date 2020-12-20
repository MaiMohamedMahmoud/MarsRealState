/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
/**
 * Retrofit creates a network API for the app based on the content from the this webservice.
 * It fetches data from the web service and routes it through a separate converter library that knows how to decode the data and return it in the form of useful objects.
 * Retrofit includes built-in support for popular web data formats such as XML and JSON. Retrofit ultimately creates most of the network layer for you, including critical details such as running the requests on background threads
 *
 */

package com.example.android.marsrealestate.network

import retrofit2.Call
import retrofit2.http.GET


interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>
}
