package com.bancom.cambix.entities.error

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ErrorMasterResponseEntities (
    @SerializedName("code")
    var code: String? = null,
    @SerializedName("httpStatus")
    var httpStatus: String? = null,
    @SerializedName("timestamp")
    var timestamp: String? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("debugMessage")
    var debugMessage: String? = null,
    @SerializedName("subErrors")
    var subErrors: String? = null
) : Serializable
