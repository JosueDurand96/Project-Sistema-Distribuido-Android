package com.durand.domain.repositories

import com.durand.domain.model.vaccination.VaccinationMasterResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure


interface VaccinationRepository {
    suspend fun getVaccination(params: String): ResultType<Failure, VaccinationMasterResponseModel>
}