package com.durand.domain.repositories

import com.durand.domain.model.local_vaccination.LocalVaccinationMasterResponseModel
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure

interface LocalVaccinationRepository {
    suspend fun getLocalVaccination(params: String): ResultType<Failure, LocalVaccinationMasterResponseModel>
}