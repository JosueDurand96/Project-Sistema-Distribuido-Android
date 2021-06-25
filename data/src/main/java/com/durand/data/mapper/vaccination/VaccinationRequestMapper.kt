package com.durand.data.mapper.vaccination

import com.durand.data.entities.vaccination.VaccinationRequestEntities
import com.durand.domain.request.VaccinationRequest

object VaccinationRequestMapper {

    fun transformRequest(productRequest: VaccinationRequest): VaccinationRequestEntities {
        val productRequestEntities = VaccinationRequestEntities()
        productRequestEntities.s_nombre = productRequest.s_nombre
        productRequestEntities.s_fabricante = productRequest.s_fabricante
        productRequestEntities.qt_dosis = productRequest.qt_dosis
        productRequestEntities.qt_dias = productRequest.qt_dias
        return productRequestEntities
    }
}