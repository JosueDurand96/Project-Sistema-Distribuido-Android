package com.durand.vacunacionperu.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Build
import com.durand.data.implements.*
import com.durand.domain.repositories.*
import com.durand.domain.usecases.*
import com.durand.vacunacionperu.ui.advance_vaccination.AdvanceVaccinationViewModel
import com.durand.vacunacionperu.ui.campaign.CampaignViewModel
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.VaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.add.AddVaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.update.UpdateVaccinationViewModel
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val applicationModule = module {

    //vaccination get
    single { GetVaccinationUseCase(get()) }
    single<VaccinationRepository> { VaccinationRepositoryImpl() }
    viewModel { VaccinationViewModel() }

    //vaccination post
    single { PostVaccinationUseCase(get()) }
    single<PostVaccinationRepository> { PostVaccinationRepositoryImpl() }
    viewModel { AddVaccinationViewModel() }

    //vaccination put
    single { PutVaccinationUseCase(get()) }
    single<PutVaccinationRepository> { PutVaccinationRepositoryImpl() }
    viewModel { UpdateVaccinationViewModel() }

    //local vaccination get
    single { GetLocalVaccinationUseCase(get()) }
    single<LocalVaccinationRepository> { LocalVaccinationRepositoryImpl() }
    viewModel { LocalVaccinationViewModel() }

    //Campaign get
    single { GetCampaignUseCase(get()) }
    single<CampaignRepository> { CampaignRepositoryImpl() }
    viewModel { CampaignViewModel() }

    //AdvanceVaccination get
    single { GetAdvanceVaccinationUseCase(get()) }
    single<AdvanceVaccinationRepository> { AdvanceVaccinationRepositoryImpl() }
    viewModel { AdvanceVaccinationViewModel() }
}