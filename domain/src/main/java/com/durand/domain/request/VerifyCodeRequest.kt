package com.bancom.cambix.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class VerifyCodeRequest (
    @SerializedName("referredCode")
    var referredCode: String? = null
):Serializable