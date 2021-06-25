package com.durand.vacunacionperu.ui.campaign

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.domain.usecases.GetCampaignUseCase
import com.durand.helper.error.Failure
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class CampaignViewModel : ViewModel(), KoinComponent {

    private val getProfessionsUseCase: GetCampaignUseCase by inject()

    private var _state: MutableLiveData<ScreenState<CampaignState>> = MutableLiveData()

    val state: LiveData<ScreenState<CampaignState>>
        get() = _state

    fun getCampaign() {
        try {
            _state.value = ScreenState.Loading
            viewModelScope.launch {
                getProfessionsUseCase.run("").either(::error, ::responseCategorie) }
        } catch (e: Exception) { }
    }

    private fun responseCategorie(list: CampaignMasterResponseModel) {
        try {
            _state.value = ScreenState.Render(CampaignState.ShowSuccess(list.data!!))
        } catch (e: Exception) { }
    }

    private fun error(failure: Failure) {
        try {
            when (failure) {
                is Failure.ServerError -> {

                    val error = failure.error as ErrorMasterResponseModel
                    _state.value =
                            ScreenState.Render(
                                    CampaignState.ShowError(
                                            error
                                    )
                            )
                    Log.d("josuecitoxd", "error:  $error")
                }
            }
        } catch (e: Exception) { }
    }
}