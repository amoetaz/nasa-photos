package com.nasa.data

import android.util.Log


import com.nasa.domain.utils.FailureStatus
import com.nasa.domain.utils.Resource

import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

open class BaseRemoteDataSource @Inject constructor() {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        try {
            Log.d("moetazTag", "safeApiCall: start")
            val apiResponse = apiCall.invoke()

            return when (apiResponse) {
                null -> {
                    Log.d("moetazTag", "safeApiCall: null")
                    Resource.Failure(FailureStatus.API_FAIL)
                }
                else -> {
                    Resource.Success(apiResponse)
                }
            }
        } catch (throwable: Throwable) {
            Log.d("moetazTag", "safeApiCall: start catch ")
            when (throwable) {
                is HttpException -> {
                    Log.d("moetazTag", "safeApiCall: HttpException")
                    return Resource.Failure(FailureStatus.API_FAIL, throwable.code(), null)
                }

                is UnknownHostException -> {
                    Log.d("moetazTag", "safeApiCall: UnknownHostException")
                    return Resource.Failure(FailureStatus.NO_INTERNET)
                }

                is ConnectException -> {
                    Log.d("moetazTag", "safeApiCall: ConnectException")

                    return Resource.Failure(FailureStatus.NO_INTERNET)
                }

                else -> {
                    Log.d("moetazTag", "safeApiCall: else Exception ${throwable.message}")

                    return Resource.Failure(FailureStatus.OTHER)
                }
            }
        }
    }
}