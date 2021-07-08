package com.durand.vacunacionperu.ui.consulta

import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.consulta.ConsultaMasterResponseModel
import com.durand.domain.model.consulta.ConsultaResponseModel

sealed class ConsultaState {
    class ShowError(val reg: ErrorMasterResponseModel) : ConsultaState()
    class ShowSuccess(val reg: ConsultaMasterResponseModel) : ConsultaState()
}