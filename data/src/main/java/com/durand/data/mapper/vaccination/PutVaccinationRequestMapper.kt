package com.durand.data.mapper.vaccination

import com.durand.data.entities.vaccination.PostVaccinationRequestEntities
import com.durand.data.entities.vaccination.PutVaccinationRequestEntities
import com.durand.domain.request.PutVaccinationRequest

object PutVaccinationRequestMapper {

    fun transformRequest(productRequest: PutVaccinationRequest): PutVaccinationRequestEntities {
        val productRequestEntities = PutVaccinationRequestEntities()
        productRequestEntities.id_vacuna = productRequest.id_vacuna
        productRequestEntities.s_nombre = productRequest.s_nombre
        productRequestEntities.s_fabricante = productRequest.s_fabricante
        productRequestEntities.qt_dosis = productRequest.qt_dosis
        productRequestEntities.qt_dias = productRequest.qt_dias
        return productRequestEntities
    }
}