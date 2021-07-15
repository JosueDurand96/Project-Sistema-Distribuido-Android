package com.durand.data.entities.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponseEntities(
    @SerializedName("s_dni")
    var s_dni: String?,
    @SerializedName("s_nombres")
    var s_nombres: String?,
    @SerializedName("s_apellidos")
    var s_apellidos: String?,
    @SerializedName("d_fec_nac")
    var d_fec_nac: String?,
    @SerializedName("s_num_celular")
    var s_num_celular: String?,
    @SerializedName("s_ubigeo_dep")
    var s_ubigeo_dep: String?,
    @SerializedName("s_ubigeo_pro")
    var s_ubigeo_pro: String?,
    @SerializedName("s_ubigeo_dis")
    var s_ubigeo_dis: String?,
    @SerializedName("s_usu_crea")
    var s_usu_crea: String?,
    @SerializedName("d_fec_crea")
    var d_fec_crea: String?,
    @SerializedName("s_usu_mod")
    var s_usu_mod: String?,
    @SerializedName("d_fec_mod")
    var d_fec_mod: String?
) : Serializable