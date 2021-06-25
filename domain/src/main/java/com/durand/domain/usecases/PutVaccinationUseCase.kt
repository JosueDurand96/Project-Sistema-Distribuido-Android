package com.durand.domain.usecases

import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.repositories.PostVaccinationRepository
import com.durand.domain.repositories.PutVaccinationRepository
import com.durand.domain.request.PutVaccinationRequest
import com.durand.domain.request.VaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure


class PutVaccinationUseCase(private val lineRepository: PutVaccinationRepository) :
    UseCase<VaccinationResponseModel, PutVaccinationRequest>() {

    override suspend fun run(params: PutVaccinationRequest): ResultType<Failure, VaccinationResponseModel> {
        return lineRepository.getVaccination(params)
    }
}