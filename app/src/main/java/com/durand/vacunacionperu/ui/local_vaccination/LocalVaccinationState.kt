package com.durand.vacunacionperu.ui.local_vaccination

import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel

sealed class LocalVaccinationState {
    class ShowError(val reg: ErrorMasterResponseModel) : LocalVaccinationState()
    class ShowSuccess(val reg: List<LocalVaccinationResponseModel>) : LocalVaccinationState()
}