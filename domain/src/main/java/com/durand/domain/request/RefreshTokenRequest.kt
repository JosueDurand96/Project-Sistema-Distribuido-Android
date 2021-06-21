package com.bancom.cambix.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RefreshTokenRequest(
    @SerializedName("refreshToken")
    var refreshToken: String? = null
) : Serializable
