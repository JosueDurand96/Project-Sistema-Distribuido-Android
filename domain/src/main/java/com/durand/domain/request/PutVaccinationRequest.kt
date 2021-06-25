package com.durand.domain.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PutVaccinationRequest(

    @SerializedName("id_vacuna")
    var id_vacuna: Int? = null,
    @SerializedName("s_nombre")
    var s_nombre: String? = null,
    @SerializedName("s_fabricante")
    var s_fabricante: String? = null,
    @SerializedName("qt_dosis")
    var qt_dosis: Int? = null,
    @SerializedName("qt_dias")
    var qt_dias: Int? = null
): Serializable