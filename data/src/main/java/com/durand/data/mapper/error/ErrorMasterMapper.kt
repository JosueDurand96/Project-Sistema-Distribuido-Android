package com.durand.data.mapper.error

import com.bancom.cambix.entities.error.ErrorMasterResponseEntities
import com.durand.data.mapper.Mapper
import com.bancom.cambix.model.error.ErrorMasterResponseModel

class ErrorMasterMapper: Mapper<ErrorMasterResponseModel, ErrorMasterResponseEntities>() {
    override fun mapToEntity(type: ErrorMasterResponseEntities): ErrorMasterResponseModel {
        return ErrorMasterResponseModel(
            type.code,
            type.httpStatus,
            type.timestamp,
            type.message,
            type.debugMessage,
            type.subErrors
        )
    }
}