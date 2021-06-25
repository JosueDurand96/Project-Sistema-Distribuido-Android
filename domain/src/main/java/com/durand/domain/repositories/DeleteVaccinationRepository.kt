package com.durand.domain.repositories

import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.request.PutVaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure

interface DeleteVaccinationRepository {
    suspend fun getVaccination(params: String): ResultType<Failure, VaccinationResponseModel>
}