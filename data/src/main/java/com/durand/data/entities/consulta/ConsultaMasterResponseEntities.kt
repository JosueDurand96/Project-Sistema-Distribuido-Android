package com.durand.data.entities.consulta

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ConsultaMasterResponseEntities (
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("origen")
    var origen: Int? = null,
    @SerializedName("data")
    var data: ConsultaResponseEntities? = null
):Serializable