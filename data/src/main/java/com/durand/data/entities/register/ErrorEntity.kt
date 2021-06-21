package com.durand.data.entities.register

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ErrorEntity(
    @SerializedName("error")
    var error:Boolean ?,
    @SerializedName("mensaje")
    var mensaje:String?
):Serializable