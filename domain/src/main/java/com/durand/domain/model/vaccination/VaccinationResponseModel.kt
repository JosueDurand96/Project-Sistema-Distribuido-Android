package com.durand.domain.model.vaccination

data class VaccinationResponseModel(
    var id_vacuna: Int? = null,
    var s_nombre: String? = null,
    var s_fabricante: String? = null,
    var qt_dosis: Int? = null,
    var qt_dias: Int? = null,
    var s_usu_crea: String? = null,
    var d_fec_crea: String? = null,
    var s_usu_mod: String? = null,
    var d_fec_mod: String? = null
)