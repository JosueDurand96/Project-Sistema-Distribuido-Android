package com.bancom.cambix.model.error

data class ErrorMasterResponseModel(
    var code: String? = null,
    var httpStatus: String? = null,
    var timestamp: String? = null,
    var message: String? = null,
    var debugMessage: String? = null,
    var subErrors: String? = null
)