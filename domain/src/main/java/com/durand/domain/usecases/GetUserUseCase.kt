package com.durand.domain.usecases

import com.durand.domain.model.consulta.ConsultaMasterResponseModel
import com.durand.domain.model.user.UserMasterResponseModel
import com.durand.domain.repositories.ConsultaRepository
import com.durand.domain.repositories.UserRepository
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure

class GetUserUseCase(private val lineRepository: UserRepository) :
    UseCase<UserMasterResponseModel, String>() {

    override suspend fun run(params: String): ResultType<Failure, UserMasterResponseModel> {
        return lineRepository.getUser(params)
    }
}