package com.durand.vacunacionperu.ui.campaign

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.usecases.DeleteCampaignUseCase
import com.durand.helper.error.Failure
import com.durand.vacunacionperu.ui.campaign.add.PostCampaignState
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class DeleteCampaignViewModel : ViewModel(), KoinComponent {

    private val putVaccinationUseCase: DeleteCampaignUseCase by inject()

    private var _state: MutableLiveData<ScreenState<PostCampaignState>> = MutableLiveData()

    val state: LiveData<ScreenState<PostCampaignState>>
        get() = _state

    fun eliminarCampana(vaccinationRequest: String) {
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
                    Log.d("josuecitoxd", "error:  $error")
                }
            }
        } catch (e: Exception) { }
    }
}