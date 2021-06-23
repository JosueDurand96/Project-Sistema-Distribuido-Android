package com.durand.data.mapper.local_vaccination

import com.durand.data.entities.local_vaccination.LocalVaccinationMasterResponseEntities
import com.durand.data.entities.local_vaccination.LocalVaccinationResponseEntities
import com.durand.data.entities.vaccination.VaccinationMasterResponseEntities
import com.durand.domain.model.local_vaccination.LocalVaccinationMasterResponseModel
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel


object LocalVaccinationMapper {

    fun transform(cardListResponse: LocalVaccinationMasterResponseEntities): LocalVaccinationMasterResponseModel {
        val cardListModel = LocalVaccinationMasterResponseModel()
        cardListModel.data = transformListCard(cardListResponse.cardResponse!!)
        return cardListModel
    }


    fun transformListCard(cardDataResponse: Array<LocalVaccinationResponseEntities>): List<LocalVaccinationResponseModel> {
        val listDocumentType: MutableList<LocalVaccinationResponseModel> = mutableListOf()
        if (cardDataResponse.isNotEmpty()) {
            for (documentTypeResponse: LocalVaccinationResponseEntities in cardDataResponse) {
                listDocumentType.add(
                        transformListCard2(documentTypeResponse)
                )
            }
        }
        return listDocumentType
    }

    fun transformListCard2(cardDataResponse: LocalVaccinationResponseEntities): LocalVaccinationResponseModel {
        val cardDataModel = LocalVaccinationResponseModel()
        cardDataModel.id_local = cardDataResponse.id_local
        cardDataModel.s_nombre = cardDataResponse.s_nombre
        cardDataModel.s_direccion = cardDataResponse.s_direccion
        cardDataModel.s_ubigeo_dep = cardDataResponse.s_ubigeo_dep
        cardDataModel.s_ubigeo_pro = cardDataResponse.s_ubigeo_pro
        cardDataModel.s_ubigeo_dis = cardDataResponse.s_ubigeo_dis
        cardDataModel.s_usu_crea = cardDataResponse.s_usu_crea
        cardDataModel.d_fec_crea = cardDataResponse.d_fec_crea
        cardDataModel.s_usu_mod = cardDataResponse.s_usu_mod
        cardDataModel.d_fec_mod = cardDataResponse.d_fec_mod
        return cardDataModel
    }
}