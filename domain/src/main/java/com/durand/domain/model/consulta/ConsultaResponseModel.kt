package com.durand.domain.model.consulta

data class ConsultaResponseModel (
    var numero: String? = null,
    var nombres: String? = null,
    var apellido_paterno: String? = null,
    var apellido_materno: String? = null,
    var sexo: String? = null,
    var codigo_verificacion: String? = null,
    var fecha_nacimiento: String? = null,
    var nombre_completo: String? = null
)