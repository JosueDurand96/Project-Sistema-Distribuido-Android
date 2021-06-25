package com.durand.data.entities.advance_vaccination

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AdvanceVaccinationResponseEntities (
        @SerializedName("id_persona_campana")
        var id_persona_campana: String? = null,
        @SerializedName("id_campana")
        var id_campana: Int? = null,
        @SerializedName("s_dni")
        var s_dni: String? = null,
        @SerializedName("i_sesion")
        var i_sesion: Int? = null,
        @SerializedName("d_fecha_hora")
        var d_fecha_hora: String? = null,
        @SerializedName("s_email")
        var s_email: String? = null,
        @SerializedName("b_completo")
        var b_completo: Boolean? = null,
        @SerializedName("s_usu_crea")
        var s_usu_crea: String? = null,
        @SerializedName("d_fec_crea")
        var d_fec_crea: String? = null,
        @SerializedName("s_usu_mod")
        var s_usu_mod: String? = null,
        @SerializedName("d_fec_mod")
        var d_fec_mod: String? = null
        ):Serializable