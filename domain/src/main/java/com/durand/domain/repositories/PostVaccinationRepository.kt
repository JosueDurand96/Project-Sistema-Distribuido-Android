package com.durand.domain.repositories

import com.durand.domain.model.vaccination.VaccinationMasterResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.request.VaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure


interface PostVaccinationRepository {
    suspend fun getVaccination(params: VaccinationRequest): ResultType<Failure, VaccinationResponseModel>
}