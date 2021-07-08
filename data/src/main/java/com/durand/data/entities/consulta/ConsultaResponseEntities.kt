package com.durand.data.entities.consulta

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ConsultaResponseEntities(
    @SerializedName("numero")
    var numero: String? = null,
    @SerializedName("nombres")
    var nombres: String? = null,
    @SerializedName("apellido_paterno")
    var apellido_paterno: String? = null,
    @SerializedName("apellido_materno")
    var apellido_materno: String? = null,
    @SerializedName("sexo")
    var sexo: String? = null,
    @SerializedName("codigo_verificacion")
    var codigo_verificacion: String? = null,
    @SerializedName("fecha_nacimiento")
    var fecha_nacimiento: String? = null,
    @SerializedName("nombre_completo")
    var nombre_completo: String?
) : Serializable