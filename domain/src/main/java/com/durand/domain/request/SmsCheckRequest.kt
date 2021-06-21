package com.bancom.cambix.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SmsCheckRequest(
    @SerializedName("code")
    var code: String? = null,
    @SerializedName("session")
    var session: String? = null,
    @SerializedName("cellphone")
    var cellphone: String? = null
) : Serializable



