package com.durand.vacunacionperu.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.domain.model.user.UserMasterResponseModel
import com.durand.domain.usecases.GetCampaignUseCase
import com.durand.domain.usecases.GetUserUseCase
import com.durand.helper.error.Failure
import com.durand.vacunacionperu.ui.campaign.CampaignState
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class UserViewModel : ViewModel(), KoinComponent {

    private val getProfessionsUseCase: GetUserUseCase by inject()

    private var _state: MutableLiveData<ScreenState<UserState>> = MutableLiveData()

    val state: LiveData<ScreenState<UserState>>
        get() = _state

    fun getUser() {
        try {
            _state.value = ScreenState.Loading
            viewModelScope.launch {
                getProfessionsUseCase.run("").either(::error, ::responseCategorie) }
        } catch (e: Exception) { }
    }

    private fun responseCategorie(list: UserMasterResponseModel) {
        try {
            _state.value = ScreenState.Render(UserState.ShowSuccess(list.data!!))
        } catch (e: Exception) { }
    }

    private fun error(failure: Failure) {
        try {
            when (failure) {
                is Failure.ServerError -> {

                    val error = failure.error as ErrorMasterResponseModel
                    _state.value =
                        ScreenState.Render(
                            UserState.ShowError(
                                error
                            )
                        )
                }
            }
        } catch (e: Exception) { }
    }
}