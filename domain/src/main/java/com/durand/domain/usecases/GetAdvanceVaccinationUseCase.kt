package com.durand.domain.usecases

import com.durand.domain.model.advanceVaccination.AdvanceVaccinationMasterResponseModel
import com.durand.domain.repositories.AdvanceVaccinationRepository
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure

class GetAdvanceVaccinationUseCase(private val lineRepository: AdvanceVaccinationRepository) :
        UseCase<AdvanceVaccinationMasterResponseModel, String>() {

    override suspend fun run(params: String): ResultType<Failure, AdvanceVaccinationMasterResponseModel> {
        return lineRepository.getAdvanceVaccination(params)
    }
}