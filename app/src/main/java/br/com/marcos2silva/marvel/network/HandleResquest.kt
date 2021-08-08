package br.com.marcos2silva.marvel.network

import android.util.Log
import com.google.gson.Gson
import retrofit2.Response
import java.io.Reader

fun <T> Response<T>.handleResponse(): Result<T?> {
    if (this.isSuccessful) {
        return Result.Success(this.body())
    }

    if (this.isSuccessful.not()) {
//        val apiError = this.errorBody()?.charStream()?.let { errorJson ->
//            Gson().fromJsonOrNull<ApiErrorResponse>(errorJson)
//        }
//        val errorApi = apiError?.error ?: run {
//            ApiError.unknown()
//        }

//        val textException = if (errorApi.title.isNullOrEmpty()) errorApi.title else errorApi.title
//        val exception = Exception(textException)
//        return Result.Error(exception)
    }

    throw Throwable()
}

inline fun <reified T> Gson.fromJsonOrNull(json: Reader): T? {
    return try {
        fromJson(json, T::class.java)
    } catch (e: Throwable) {
        e.message?.let { Log.e("", it) }
        null
    }
}