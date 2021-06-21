package com.durand.vacunacionperu.ui.vaccination

import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel

sealed class VaccinationState {
    class ShowError(val reg: ErrorMasterResponseModel) : VaccinationState()
    class ShowSuccess(val reg: List<VaccinationResponseModel>) : VaccinationState()
}