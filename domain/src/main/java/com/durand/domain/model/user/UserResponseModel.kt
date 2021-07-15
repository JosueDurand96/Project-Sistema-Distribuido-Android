package com.durand.domain.model.user

data class UserResponseModel(
    var s_dni: String? = null,
    var s_nombres: String? = null,
    var s_apellidos: String? = null,
    var d_fec_nac: String? = null,
    var s_num_celular: String? = null,
    var s_ubigeo_dep: String? = null,
    var s_ubigeo_pro: String? = null,
    var s_ubigeo_dis: String? = null,
    var s_usu_crea: String? = null,
    var d_fec_crea: String? = null,
    var s_usu_mod: String?? = null,
    var d_fec_mod: String? = null
)