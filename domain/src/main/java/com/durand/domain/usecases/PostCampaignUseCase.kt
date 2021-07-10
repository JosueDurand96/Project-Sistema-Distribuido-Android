package com.durand.domain.usecases

import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.repositories.PostCampaignRepository
import com.durand.domain.repositories.PostVaccinationRepository
import com.durand.domain.request.CampaignRequest
import com.durand.domain.request.VaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure


class PostCampaignUseCase(private val lineRepository: PostCampaignRepository) :
    UseCase<CampaignResponseModel, CampaignRequest>() {

    override suspend fun run(params: CampaignRequest): ResultType<Failure, CampaignResponseModel> {
        return lineRepository.getCampaign(params)
    }
}