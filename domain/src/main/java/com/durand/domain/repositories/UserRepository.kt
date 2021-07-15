package com.durand.domain.repositories

import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.domain.model.user.UserMasterResponseModel
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure

interface UserRepository {
    suspend fun getUser(params: String): ResultType<Failure, UserMasterResponseModel>
}