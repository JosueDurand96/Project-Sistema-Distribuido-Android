package com.durand.domain.repositories

import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.request.PutVaccinationRequest
import com.durand.domain.request.VaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure

interface PutVaccinationRepository {
    suspend fun getVaccination(params: PutVaccinationRequest): ResultType<Failure, VaccinationResponseModel>
}