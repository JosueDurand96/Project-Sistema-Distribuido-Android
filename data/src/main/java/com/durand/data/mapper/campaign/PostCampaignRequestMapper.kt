package com.durand.data.mapper.campaign

import com.durand.data.entities.campaign.PostCampaignRequestEntities
import com.durand.data.entities.vaccination.PostVaccinationRequestEntities
import com.durand.domain.request.CampaignRequest
import com.durand.domain.request.VaccinationRequest

object PostCampaignRequestMapper {
    fun transformRequest(productRequest: CampaignRequest): PostCampaignRequestEntities {
        val productRequestEntities = PostCampaignRequestEntities()
        productRequestEntities.s_nombre = productRequest.s_nombre
        productRequestEntities.d_fec_inicio = productRequest.d_fec_inicio
        productRequestEntities.id_vacuna = productRequest.id_vacuna
        productRequestEntities.id_local = productRequest.id_local
        productRequestEntities.qt_dosis_disponible = productRequest.qt_dosis_disponible
        productRequestEntities.b_envio_notificacion = productRequest.b_envio_notificacion
        productRequestEntities.s_usu_crea = productRequest.s_usu_crea

        return productRequestEntities
    }
}