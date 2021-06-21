package com.bancom.cambix.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserRequest(
    @SerializedName("token")
    var token: String? = null
): Serializable


