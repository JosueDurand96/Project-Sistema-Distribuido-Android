package com.bancom.cambix.request


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RegisterRequest(
    @SerializedName("acceptPersonalDataProcessing")
    var acceptPersonalDataProcessing: Boolean? = null,
    @SerializedName("cellphone")
    var cellphone: String? = null,
    @SerializedName("checkDigit")
    var checkDigit: String? = null,
    @SerializedName("documentNumber")
    var documentNumber: String? = null,
    @SerializedName("documentTypeCode")
    var documentTypeCode: String? = null,
    @SerializedName("idReferred")
    var idReferred: String? = null,
    @SerializedName("mail")
    var mail: String? = null,
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("platformCode")
    var platformCode: String? = null
) : Serializable