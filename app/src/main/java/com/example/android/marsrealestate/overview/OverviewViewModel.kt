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

package com.example.android.marsrealestate.overview

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsApiFilter
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _responseMarsObj = MutableLiveData<List<MarsProperty>>()

    // The external immutable LiveData for the request status String
    val responseMarsProperty: LiveData<List<MarsProperty>>
        get() = _responseMarsObj

    private val _status_navigate = MutableLiveData<MarsProperty>()

    val status_navigate: LiveData<MarsProperty>
        get() = _status_navigate

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        _status_navigate.value = null
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL.value)
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties(show: String) {

        /**
         * All coroutine launched in this scope is automatically canceled if the ViewModel is cleared.
         */
        viewModelScope.launch {
            try {
                val propertyList = MarsApi.retrofitService.getProperties(show)
                _responseMarsObj.value = propertyList
                Log.i("ViewModel", _responseMarsObj.value.toString())
            } catch (e: Exception) {
                e.message
            }
        }
        /**
         * this was the old way of using retrofit with callBacks pattern now
         * Using corotines insted of callback ...
         */
//        MarsApi.retrofitService.getProperties().enqueue(object : Callback<String> {
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                _response.value = t.message
//            }
//
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                _response.value = response.body()
//            }
//
//        })


    }


    fun setStatusNavigate(marsProperty: MarsProperty) {
        _status_navigate.value = marsProperty
    }

    fun clearStatusNavigate() {
        _status_navigate.value = null
    }

    fun updateFilter(show: String) {
        getMarsRealEstateProperties(show)
    }
}
