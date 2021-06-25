package com.durand.data.mapper.advance_vaccination

import com.durand.data.entities.advance_vaccination.AdvanceVaccinationMasterResponseEntities
import com.durand.data.entities.advance_vaccination.AdvanceVaccinationResponseEntities
import com.durand.domain.model.advanceVaccination.AdvanceVaccinationMasterResponseModel
import com.durand.domain.model.advanceVaccination.AdvanceVaccinationResponseModel

object AdvanceVaccinationMapper {

    fun transform(cardListResponse: AdvanceVaccinationMasterResponseEntities): AdvanceVaccinationMasterResponseModel {
        val cardListModel = AdvanceVaccinationMasterResponseModel()
        cardListModel.data = transformListCard(cardListResponse.cardResponse!!)
        return cardListModel
    }


    fun transformListCard(cardDataResponse: Array<AdvanceVaccinationResponseEntities>): List<AdvanceVaccinationResponseModel> {
        val listDocumentType: MutableList<AdvanceVaccinationResponseModel> = mutableListOf()
        if (cardDataResponse.isNotEmpty()) {
            for (documentTypeResponse: AdvanceVaccinationResponseEntities in cardDataResponse) {
                listDocumentType.add(
                        transformListCard2(documentTypeResponse)
                )
            }
        }
        return listDocumentType
    }

    fun transformListCard2(cardDataResponse: AdvanceVaccinationResponseEntities): AdvanceVaccinationResponseModel {
        val cardDataModel = AdvanceVaccinationResponseModel()
        cardDataModel.id_persona_campana = cardDataResponse.id_persona_campana
        cardDataModel.id_campana = cardDataResponse.id_campana
        cardDataModel.s_dni = cardDataResponse.s_dni
        cardDataModel.i_sesion = cardDataResponse.i_sesion
        cardDataModel.d_fecha_hora = cardDataResponse.d_fecha_hora
        cardDataModel.s_email = cardDataResponse.s_email
        cardDataModel.b_completo = cardDataResponse.b_completo
        cardDataModel.s_usu_crea = cardDataResponse.s_usu_crea
        cardDataModel.d_fec_crea = cardDataResponse.d_fec_crea
        cardDataModel.s_usu_mod = cardDataResponse.s_usu_mod
        cardDataModel.d_fec_mod = cardDataResponse.d_fec_mod
        return cardDataModel
    }
}