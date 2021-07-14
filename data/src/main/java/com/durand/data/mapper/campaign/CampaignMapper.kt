package com.durand.data.mapper.campaign

import com.durand.data.entities.campaign.CampaignMasterResponseEntities
import com.durand.data.entities.campaign.CampaignResponseEntities
import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.domain.model.campaign.CampaignResponseModel


object CampaignMapper {
    fun transform(cardListResponse: CampaignMasterResponseEntities): CampaignMasterResponseModel {
        val cardListModel = CampaignMasterResponseModel()
        cardListModel.data = transformListCard(cardListResponse.cardResponse!!)
        return cardListModel
    }


    fun transformListCard(cardDataResponse: Array<CampaignResponseEntities>): List<CampaignResponseModel> {
        val listDocumentType: MutableList<CampaignResponseModel> = mutableListOf()
        if (cardDataResponse.isNotEmpty()) {
            for (documentTypeResponse: CampaignResponseEntities in cardDataResponse) {
                listDocumentType.add(
                        transformListCard2(documentTypeResponse)
                )
            }
        }
        return listDocumentType
    }

    fun transformListCard2(cardDataResponse: CampaignResponseEntities): CampaignResponseModel {
        val cardDataModel = CampaignResponseModel()
        cardDataModel.id_campana = cardDataResponse.id_campana
        cardDataModel.s_nombre = cardDataResponse.s_nombre
        cardDataModel.d_fec_inicio = cardDataResponse.d_fec_inicio
        cardDataModel.id_vacuna = cardDataResponse.id_vacuna
        cardDataModel.id_local = cardDataResponse.id_local
        cardDataModel.qt_dosis_disponible = cardDataResponse.qt_dosis_disponible
        cardDataModel.b_envio_notificacion = cardDataResponse.b_envio_notificacion
        cardDataModel.s_usu_crea = cardDataResponse.d_fec_crea
        cardDataModel.d_fec_crea = cardDataResponse.s_usu_mod
        cardDataModel.s_usu_mod = cardDataResponse.d_fec_mod
        cardDataModel.d_fec_mod = cardDataResponse.d_fec_mod
        return cardDataModel
    }
}