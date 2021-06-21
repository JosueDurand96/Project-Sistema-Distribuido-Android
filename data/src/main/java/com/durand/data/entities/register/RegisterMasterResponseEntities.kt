package com.durand.data.entities.register

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RegisterMasterResponseEntities(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("isClient")
    var isClient: Boolean? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("lastName")
    var lastName: String? = null,
    @SerializedName("motherLastName")
    var motherLastName: String? = null
) : Serializable
