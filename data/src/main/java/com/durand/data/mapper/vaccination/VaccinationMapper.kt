package com.durand.data.mapper.vaccination

import com.durand.data.entities.vaccination.VaccinationMasterResponseEntities
import com.durand.data.entities.vaccination.VaccinationResponseEntities
import com.durand.domain.model.vaccination.VaccinationMasterResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel

object VaccinationMapper {

    fun transform(cardListResponse: VaccinationMasterResponseEntities): VaccinationMasterResponseModel {
        val cardListModel = VaccinationMasterResponseModel()
        cardListModel.data = transformListCard(cardListResponse.cardResponse!!)
        return cardListModel
    }


    fun transformListCard(cardDataResponse: Array<VaccinationResponseEntities>): List<VaccinationResponseModel> {
        val listDocumentType: MutableList<VaccinationResponseModel> = mutableListOf()
        if (cardDataResponse.isNotEmpty()) {
            for (documentTypeResponse: VaccinationResponseEntities in cardDataResponse) {
                listDocumentType.add(
                    transformListCard2(documentTypeResponse)
                )
            }
        }
        return listDocumentType
    }

    fun transformListCard2(cardDataResponse: VaccinationResponseEntities): VaccinationResponseModel {
        val cardDataModel = VaccinationResponseModel()
        cardDataModel.id_vacuna = cardDataResponse.id_vacuna
        cardDataModel.s_nombre = cardDataResponse.s_nombre
        cardDataModel.s_fabricante = cardDataResponse.s_fabricante
        cardDataModel.qt_dosis = cardDataResponse.qt_dosis

        cardDataModel.qt_dias = cardDataResponse.qt_dias
        cardDataModel.s_usu_crea = cardDataResponse.s_usu_crea
        cardDataModel.d_fec_crea = cardDataResponse.d_fec_crea
        cardDataModel.s_usu_mod = cardDataResponse.s_usu_mod
        cardDataModel.d_fec_mod = cardDataResponse.d_fec_mod
        return cardDataModel
    }

}