package com.durand.domain.model.consulta

data class ConsultaMasterResponseModel (
    var success: Boolean? = null,
    var origen: Int? = null,
    var data: ConsultaResponseModel? = null
)