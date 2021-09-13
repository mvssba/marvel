package br.com.marcos2silva.network.model

import com.google.gson.annotations.SerializedName

data class ErrorHandle(
    @SerializedName("code")
    val code: Int,

    @SerializedName("status")
    val message: String
)

