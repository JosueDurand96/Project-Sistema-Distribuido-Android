package com.durand.data.entities.vaccination

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VaccinationResponseEntities(
    @SerializedName("id_vacuna")
    var id_vacuna: Int? = null,
    @SerializedName("s_nombre")
    var s_nombre: String? = null,
    @SerializedName("s_fabricante")
    var s_fabricante: String? = null,
    @SerializedName("qt_dosis")
    var qt_dosis: Int? = null,
    @SerializedName("qt_dias")
    var qt_dias: Int? = null,
    @SerializedName("s_usu_crea")
    var s_usu_crea: String? = null,
    @SerializedName("d_fec_crea")
    var d_fec_crea: String? = null,
    @SerializedName("s_usu_mod")
    var s_usu_mod: String? = null,
    @SerializedName("d_fec_mod")
    var d_fec_mod: String? = null
) : Serializable