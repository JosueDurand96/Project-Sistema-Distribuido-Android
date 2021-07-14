package com.durand.vacunacionperu.di

import com.durand.data.implements.*
import com.durand.domain.repositories.*
import com.durand.domain.usecases.*
import com.durand.vacunacionperu.ui.advance_vaccination.AdvanceVaccinationViewModel
import com.durand.vacunacionperu.ui.campaign.CampaignViewModel
import com.durand.vacunacionperu.ui.campaign.DeleteCampaignViewModel
import com.durand.vacunacionperu.ui.campaign.add.PostCampaignViewModel
import com.durand.vacunacionperu.ui.consulta.ConsultaDniViewModel
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.DeleteVaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.VaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.add.PostVaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.update.UpdateVaccinationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    //vaccination get
    single { GetVaccinationUseCase(get()) }
    single<VaccinationRepository> { VaccinationRepositoryImpl() }
    viewModel { VaccinationViewModel() }

    //vaccination post
    single { PostVaccinationUseCase(get()) }
    single<PostVaccinationRepository> { PostVaccinationRepositoryImpl() }
    viewModel { PostVaccinationViewModel() }

    //vaccination put
    single { PutVaccinationUseCase(get()) }
    single<PutVaccinationRepository> { PutVaccinationRepositoryImpl() }
    viewModel { UpdateVaccinationViewModel() }

    //vaccination delete
    single { DeleteVaccinationUseCase(get()) }
    single<DeleteVaccinationRepository> { DeleteVaccinationRepositoryImpl() }
    viewModel { DeleteVaccinationViewModel() }

    //local vaccination get
    single { GetLocalVaccinationUseCase(get()) }
    single<LocalVaccinationRepository> { LocalVaccinationRepositoryImpl() }
    viewModel { LocalVaccinationViewModel() }

    //Campaign get
    single { GetCampaignUseCase(get()) }
    single<CampaignRepository> { CampaignRepositoryImpl() }
    viewModel { CampaignViewModel() }
    //Campaign post
    single { PostCampaignUseCase(get()) }
    single<PostCampaignRepository> { PostCampaignRepositoryImpl() }
    viewModel { PostCampaignViewModel() }
    //vaccination delete
    single { DeleteCampaignUseCase(get()) }
    single<DeleteCampaignRepository> { DeleteCampaignRepositoryImpl() }
    viewModel { DeleteCampaignViewModel() }

    //AdvanceVaccination get
    single { GetAdvanceVaccinationUseCase(get()) }
    single<AdvanceVaccinationRepository> { AdvanceVaccinationRepositoryImpl() }
    viewModel { AdvanceVaccinationViewModel() }

    //API externa fragment
    single { GetConsultaDniUseCase(get()) }
    single<ConsultaRepository> { ConsultaRepositoryImpl() }
    viewModel { ConsultaDniViewModel() }
}