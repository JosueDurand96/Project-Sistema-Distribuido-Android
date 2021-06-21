package com.bancom.cambix.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VerifyDniRequest(
    @SerializedName("cellphone")
    var cellphone: String,
    @SerializedName("front")
    var front: String,
    @SerializedName("later")
    var later: String
) : Serializable