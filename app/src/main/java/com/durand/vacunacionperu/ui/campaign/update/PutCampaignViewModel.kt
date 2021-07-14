package com.durand.vacunacionperu.ui.campaign.update

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.request.PutCampaignRequest
import com.durand.domain.request.PutVaccinationRequest
import com.durand.domain.usecases.PutCampaignUseCase
import com.durand.domain.usecases.PutVaccinationUseCase
import com.durand.helper.error.Failure
import com.durand.vacunacionperu.ui.campaign.add.PostCampaignState
import com.durand.vacunacionperu.ui.vaccination.add.AddVaccinationState
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class PutCampaignViewModel : ViewModel(), KoinComponent {

    private val putVaccinationUseCase: PutCampaignUseCase by inject()

    private var _state: MutableLiveData<ScreenState<PostCampaignState>> = MutableLiveData()

    val state: LiveData<ScreenState<PostCampaignState>>
        get() = _state

    fun putVaccination(vaccinationRequest: PutCampaignRequest) {
        try {
            _state.value = ScreenState.Loading
            viewModelScope.launch {
                putVaccinationUseCase.run(vaccinationRequest).either(::error, ::responseCategorie) }
        } catch (e: Exception) { }
    }

    private fun responseCategorie(list: CampaignResponseModel) {
        try {
            _state.value = ScreenState.Render(PostCampaignState.ShowSuccess(list))
        } catch (e: Exception) { }
    }

    private fun error(failure: Failure) {
        try {
            when (failure) {
                is Failure.ServerError -> {

                    val error = failure.error as ErrorMasterResponseModel
                    _state.value =
                        ScreenState.Render(
                            PostCampaignState.ShowError(
                                error
                            )
                        )
                }
            }
        } catch (e: Exception) { }
    }
}