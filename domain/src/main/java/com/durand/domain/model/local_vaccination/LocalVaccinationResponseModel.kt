package com.durand.domain.model.local_vaccination

data class LocalVaccinationResponseModel(
        var id_local: Int? = null,
        var s_nombre: String? = null,
        var s_direccion: String? = null,
        var s_ubigeo_dep: String? = null,
        var s_ubigeo_pro: String? = null,
        var s_ubigeo_dis: String? = null,
        var s_usu_crea: String? = null,
        var d_fec_crea: String? = null,
        var s_usu_mod: String? = null,
        var d_fec_mod: String? = null
)

