package com.bancom.cambix.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class VerifyPhoneRequest(
    @SerializedName("cellphone")
    var cellphone: String? = null,
    @SerializedName("documentNumber")
    var documentNumber: String? = null
) : Serializable