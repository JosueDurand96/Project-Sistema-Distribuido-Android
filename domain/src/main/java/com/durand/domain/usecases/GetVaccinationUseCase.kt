package com.durand.domain.usecases

import com.durand.domain.model.vaccination.VaccinationMasterResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.repositories.VaccinationRepository
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure


class GetVaccinationUseCase(private val lineRepository: VaccinationRepository) :
    UseCase<VaccinationMasterResponseModel, String>() {

    override suspend fun run(params: String): ResultType<Failure, VaccinationMasterResponseModel> {
        return lineRepository.getVaccination(params)
    }
}