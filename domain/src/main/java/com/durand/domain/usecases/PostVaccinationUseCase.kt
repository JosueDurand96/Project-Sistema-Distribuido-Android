package com.durand.domain.usecases

import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.repositories.PostVaccinationRepository
import com.durand.domain.request.VaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure

class PostVaccinationUseCase(private val lineRepository: PostVaccinationRepository) :
    UseCase<VaccinationResponseModel, VaccinationRequest>() {

    override suspend fun run(params: VaccinationRequest): ResultType<Failure, VaccinationResponseModel> {
        return lineRepository.getVaccination(params)
    }
}