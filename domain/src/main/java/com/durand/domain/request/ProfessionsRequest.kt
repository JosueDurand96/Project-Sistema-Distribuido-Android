package com.bancom.cambix.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProfessionsRequest(
    @SerializedName("name")
    var name: String? = null
): Serializable


