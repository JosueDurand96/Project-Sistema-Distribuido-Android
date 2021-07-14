package com.durand.data.mapper.campaign

import com.durand.data.entities.campaign.PutCampaignRequestEntities
import com.durand.data.entities.vaccination.PutVaccinationRequestEntities
import com.durand.domain.request.PutCampaignRequest
import com.durand.domain.request.PutVaccinationRequest

object PutCampaignRequestMapper {
    fun transformRequest(productRequest: PutCampaignRequest): PutCampaignRequestEntities {
        val productRequestEntities = PutCampaignRequestEntities()
        productRequestEntities.id_campana = productRequest.id_campana
        productRequestEntities.s_nombre = productRequest.s_nombre
        productRequestEntities.d_fec_inicio = productRequest.d_fec_inicio
        productRequestEntities.id_vacuna = productRequest.id_vacuna
        productRequestEntities.id_local = productRequest.id_local
        productRequestEntities.qt_dosis_disponible = productRequest.qt_dosis_disponible
        productRequestEntities.b_envio_notificacion = productRequest.b_envio_notificacion
        productRequestEntities.s_usu_crea = productRequest.s_usu_crea
        productRequestEntities.d_fec_crea = productRequest.d_fec_crea
        return productRequestEntities
    }
}