package com.bancom.cambix.request


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SaveProfessionsRequest(
    @SerializedName("cellphone")
    var cellphone: String,
    @SerializedName("codeProfession")
    var codeProfession: String,
    @SerializedName("nameProfession")
    var nameProfession: String
) : Serializable