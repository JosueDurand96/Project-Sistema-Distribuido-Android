package com.durand.domain.repositories

import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.domain.model.consulta.ConsultaMasterResponseModel
import com.durand.domain.model.consulta.ConsultaResponseModel
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure


interface ConsultaRepository {
    suspend fun getConsultaDni(params: String): ResultType<Failure, ConsultaMasterResponseModel>
}