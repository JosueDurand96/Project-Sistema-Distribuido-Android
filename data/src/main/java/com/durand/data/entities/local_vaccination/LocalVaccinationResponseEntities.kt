package com.durand.data.entities.local_vaccination

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LocalVaccinationResponseEntities(
        @SerializedName("id_local")
        var id_local: Int? = null,
        @SerializedName("s_nombre")
        var s_nombre: String? = null,
        @SerializedName("s_direccion")
        var s_direccion: String? = null,
        @SerializedName("s_ubigeo_dep")
        var s_ubigeo_dep: String? = null,
        @SerializedName("s_ubigeo_pro")
        var s_ubigeo_pro: String? = null,
        @SerializedName("s_ubigeo_dis")
        var s_ubigeo_dis: String? = null,
        @SerializedName("s_usu_crea")
        var s_usu_crea: String? = null,
        @SerializedName("d_fec_crea")
        var d_fec_crea: String? = null,
        @SerializedName("s_usu_mod")
        var s_usu_mod: String? = null,
        @SerializedName("d_fec_mod")
        var d_fec_mod: String? = null
) : Serializable