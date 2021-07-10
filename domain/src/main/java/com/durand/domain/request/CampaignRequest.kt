package com.durand.domain.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CampaignRequest(
    @SerializedName("s_nombre")
    var s_nombre: String? = null,
    @SerializedName("d_fec_inicio")
    var d_fec_inicio: String? = null,
    @SerializedName("id_vacuna")
    var id_vacuna: Int? = null,
    @SerializedName("id_local")
    var id_local: Int? = null,
    @SerializedName("qt_dosis_disponible")
    var qt_dosis_disponible: Int? = null,
    @SerializedName("b_envio_notificacion")
    var b_envio_notificacion: Boolean? = null,
    @SerializedName("s_usu_crea")
    var s_usu_crea: String? = null
) : Serializable