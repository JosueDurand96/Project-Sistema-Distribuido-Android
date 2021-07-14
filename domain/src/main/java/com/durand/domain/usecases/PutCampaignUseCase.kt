package com.durand.domain.usecases

import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.repositories.PutCampaignRepository
import com.durand.domain.repositories.PutVaccinationRepository
import com.durand.domain.request.PutCampaignRequest
import com.durand.domain.request.PutVaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure

class PutCampaignUseCase(private val lineRepository: PutCampaignRepository) :
    UseCase<CampaignResponseModel, PutCampaignRequest>() {

    override suspend fun run(params: PutCampaignRequest): ResultType<Failure, CampaignResponseModel> {
        return lineRepository.getCampaign(params)
    }
}