package com.durand.data.mapper.error

import com.bancom.cambix.model.register.RegisterModel
import com.durand.data.entities.register.ErrorEntity
import com.durand.data.mapper.Mapper


class ErrorMapper: Mapper<RegisterModel, ErrorEntity>() {

    override fun mapToEntity(type: ErrorEntity): RegisterModel {
        return RegisterModel(type.error,type.mensaje)
    }
}