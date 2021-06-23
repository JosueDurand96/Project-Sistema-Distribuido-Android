package com.durand.domain.model.local_vaccination

import com.durand.domain.model.vaccination.VaccinationResponseModel


data class LocalVaccinationMasterResponseModel(
        var data: List<LocalVaccinationResponseModel>? = null
)