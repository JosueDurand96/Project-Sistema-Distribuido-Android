package com.durand.vacunacionperu.ui.users

import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.user.UserResponseModel


sealed class UserState {
    class ShowError(val reg: ErrorMasterResponseModel) : UserState()
    class ShowSuccess(val reg: List<UserResponseModel>) : UserState()
}