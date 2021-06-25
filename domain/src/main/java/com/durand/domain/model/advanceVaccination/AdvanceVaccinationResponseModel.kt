package com.durand.domain.model.advanceVaccination


data class AdvanceVaccinationResponseModel(
        var id_persona_campana: String? = null,
        var id_campana: Int? = null,
        var s_dni: String? = null,
        var i_sesion: Int? = null,
        var d_fecha_hora: String? = null,
        var s_email: String? = null,
        var b_completo: Boolean? = null,
        var s_usu_crea: String? = null,
        var d_fec_crea: String? = null,
        var s_usu_mod: String? = null,
        var d_fec_mod: String? = null
)