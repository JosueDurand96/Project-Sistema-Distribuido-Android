package com.durand.domain.usecases

import com.durand.domain.model.consulta.ConsultaMasterResponseModel
import com.durand.domain.model.consulta.ConsultaResponseModel
import com.durand.domain.model.local_vaccination.LocalVaccinationMasterResponseModel
import com.durand.domain.repositories.ConsultaRepository
import com.durand.domain.repositories.LocalVaccinationRepository
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure

class GetConsultaDniUseCase(private val lineRepository: ConsultaRepository) :
    UseCase<ConsultaMasterResponseModel, String>() {

    override suspend fun run(params: String): ResultType<Failure, ConsultaMasterResponseModel> {
        return lineRepository.getConsultaDni(params)
    }
}