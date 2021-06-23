package com.durand.domain.usecases

import com.durand.domain.model.local_vaccination.LocalVaccinationMasterResponseModel
import com.durand.domain.model.vaccination.VaccinationMasterResponseModel
import com.durand.domain.repositories.LocalVaccinationRepository
import com.durand.domain.repositories.VaccinationRepository
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure

class GetLocalVaccinationUseCase(private val lineRepository: LocalVaccinationRepository) :
        UseCase<LocalVaccinationMasterResponseModel, String>() {

    override suspend fun run(params: String): ResultType<Failure, LocalVaccinationMasterResponseModel> {
        return lineRepository.getLocalVaccination(params)
    }
}