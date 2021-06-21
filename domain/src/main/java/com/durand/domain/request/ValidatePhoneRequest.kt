package com.bancom.cambix.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ValidatePhoneRequest(
    @SerializedName("cellphone")
    var cellphone: String? = null
) : Serializable


