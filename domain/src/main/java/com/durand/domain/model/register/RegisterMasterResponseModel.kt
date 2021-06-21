package com.bancom.cambix.model.register

data class RegisterMasterResponseModel (
    var id: Int? = null,
    var isClient: Boolean? = null,
    var name: String? = null,
    var lastName: String? = null,
    var motherLastName: String? = null
)