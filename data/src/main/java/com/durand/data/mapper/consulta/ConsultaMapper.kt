package com.durand.data.mapper.consulta

import com.durand.data.entities.consulta.ConsultaMasterResponseEntities
import com.durand.data.entities.consulta.ConsultaResponseEntities
import com.durand.data.entities.local_vaccination.LocalVaccinationMasterResponseEntities
import com.durand.data.entities.local_vaccination.LocalVaccinationResponseEntities
import com.durand.data.mapper.local_vaccination.LocalVaccinationMapper
import com.durand.domain.model.consulta.ConsultaMasterResponseModel
import com.durand.domain.model.consulta.ConsultaResponseModel
import com.durand.domain.model.local_vaccination.LocalVaccinationMasterResponseModel
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel

object ConsultaMapper {
    fun transform(cardListResponse: ConsultaMasterResponseEntities): ConsultaMasterResponseModel {
        val cardListModel = ConsultaMasterResponseModel()
        cardListModel.success = cardListResponse.success
        cardListModel.origen = cardListResponse.origen
        cardListModel.data = transformListCard2(cardListResponse.data!!)
        return cardListModel
    }

    fun transformListCard2(cardDataResponse: ConsultaResponseEntities): ConsultaResponseModel {
        val cardDataModel = ConsultaResponseModel()
        cardDataModel.numero = cardDataResponse.numero
        cardDataModel.nombres = cardDataResponse.nombres
        cardDataModel.apellido_paterno = cardDataResponse.apellido_paterno
        cardDataModel.apellido_materno = cardDataResponse.apellido_materno
        cardDataModel.sexo = cardDataResponse.sexo
        cardDataModel.codigo_verificacion = cardDataResponse.codigo_verificacion
        cardDataModel.fecha_nacimiento = cardDataResponse.fecha_nacimiento
        cardDataModel.nombre_completo = cardDataResponse.nombre_completo
        return cardDataModel
    }
}