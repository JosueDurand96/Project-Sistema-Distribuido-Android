package com.durand.domain.model.campaign

data class CampaignResponseModel(
        var id_campana: Int? = null,
        var s_nombre: String? = null,
        var d_fec_inicio: String? = null,
        var id_vacuna: Int? = null,
        var id_local: Int? = null,
        var qt_dosis_disponible: Int? = null,
        var b_envio_notificacion: Boolean? = null,
        var s_usu_crea: String? = null,
        var d_fec_crea: String? = null,
        var s_usu_mod: String? = null,
        var d_fec_mod: String? = null
)