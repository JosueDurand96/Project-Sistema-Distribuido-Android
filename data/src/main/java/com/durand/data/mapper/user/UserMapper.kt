package com.durand.data.mapper.user

import com.durand.data.entities.user.UserMasterResponseEntities
import com.durand.data.entities.user.UserResponseEntities
import com.durand.domain.model.user.UserMasterResponseModel
import com.durand.domain.model.user.UserResponseModel


object UserMapper {

    fun transform(cardListResponse: UserMasterResponseEntities): UserMasterResponseModel {
        val cardListModel = UserMasterResponseModel()
        cardListModel.data = transformListCard(cardListResponse.cardResponse!!)
        return cardListModel
    }


    fun transformListCard(cardDataResponse: Array<UserResponseEntities>): List<UserResponseModel> {
        val listDocumentType: MutableList<UserResponseModel> = mutableListOf()
        if (cardDataResponse.isNotEmpty()) {
            for (documentTypeResponse: UserResponseEntities in cardDataResponse) {
                listDocumentType.add(
                    transformListCard2(documentTypeResponse)
                )
            }
        }
        return listDocumentType
    }

    fun transformListCard2(cardDataResponse: UserResponseEntities): UserResponseModel {
        val cardDataModel = UserResponseModel()
        cardDataModel.s_dni = cardDataResponse.s_dni
        cardDataModel.s_nombres = cardDataResponse.s_nombres
        cardDataModel.s_apellidos = cardDataResponse.s_apellidos
        cardDataModel.d_fec_nac = cardDataResponse.d_fec_nac
        cardDataModel.s_num_celular = cardDataResponse.s_num_celular
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