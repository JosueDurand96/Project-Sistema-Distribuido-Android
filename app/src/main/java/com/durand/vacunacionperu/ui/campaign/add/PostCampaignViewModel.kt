package com.durand.vacunacionperu.ui.campaign.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.request.CampaignRequest
import com.durand.domain.usecases.PostCampaignUseCase
import com.durand.helper.error.Failure
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class PostCampaignViewModel  : ViewModel(), KoinComponent {

    private val getProfessionsUseCase: PostCampaignUseCase by inject()

    private var _state: MutableLiveData<ScreenState<PostCampaignState>> = MutableLiveData()

    val state: LiveData<ScreenState<PostCampaignState>>
        get() = _state

    fun postCampaign(vaccinationRequest: CampaignRequest) {
        try {
            _state.value = ScreenState.Loading
            viewModelScope.launch {
                getProfessionsUseCase.run(vaccinationRequest).either(::error, ::responseCategorie) }
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