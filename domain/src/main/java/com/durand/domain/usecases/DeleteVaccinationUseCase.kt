package com.durand.domain.usecases

import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.repositories.DeleteVaccinationRepository
import com.durand.domain.repositories.PutVaccinationRepository
import com.durand.domain.request.PutVaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure

class DeleteVaccinationUseCase(private val lineRepository: DeleteVaccinationRepository) :
    UseCase<VaccinationResponseModel, String>() {

    override suspend fun run(params: String): ResultType<Failure, VaccinationResponseModel> {
        return lineRepository.getVaccination(params)
    }
}