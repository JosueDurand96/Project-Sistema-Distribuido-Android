package com.bancom.cambix.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class DniNoValidationRequest(
    @SerializedName("documentNumber")
    var documentNumber: String? = null
) : Serializable


