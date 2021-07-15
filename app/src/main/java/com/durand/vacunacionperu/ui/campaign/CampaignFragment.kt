package com.durand.vacunacionperu.ui.campaign

import android.app.Activity
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.room.AppLocalDatabase
import com.durand.vacunacionperu.room.LocalVacunacion
import com.durand.vacunacionperu.room.LocalVacunacionDao
import com.durand.vacunacionperu.ui.campaign.add.PostCampaignState
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationState
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.VaccinationAdapter
import com.durand.vacunacionperu.ui.vaccination.VaccinationState
import com.durand.vacunacionperu.ui.vaccination.VaccinationViewModel
import com.durand.vacunacionperu.util.ScreenState
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_campaign_fragment.*
import kotlinx.android.synthetic.main.fragment_campaign.*
import kotlinx.android.synthetic.main.fragment_vaccination.*
import kotlinx.android.synthetic.main.fragment_vaccination.vaccinationProgressBar

class CampaignFragment : BaseFragment() {

    private lateinit var campaignViewModel: CampaignViewModel
    private lateinit var campaignAdapter: CampaignAdapter
    private lateinit var campaignRecyclerView: RecyclerView
    private lateinit var campaignFab: FloatingActionButton
    private lateinit var localVacunacionDao: LocalVacunacionDao
    private lateinit var localVaccinationViewModel: LocalVaccinationViewModel
    private lateinit var deleteCampaignViewModel: DeleteCampaignViewModel
    private lateinit var mroot: CoordinatorLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deleteCampaignViewModel = ViewModelProvider(this).get(DeleteCampaignViewModel::class.java)
        localVacunacionDao = AppLocalDatabase.getInstance(context as Activity).localDao()
        campaignViewModel = ViewModelProvider(this).get(CampaignViewModel::class.java)
        localVaccinationViewModel =
            ViewModelProvider(this).get(LocalVaccinationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_campaign, container, false)
        campaignRecyclerView = root.findViewById(R.id.campaignRecyclerView)
        campaignFab = root.findViewById(R.id.campaignFab)
        mroot = root.findViewById(R.id.mrootCamp)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        campaignProgressBar.visibility = View.VISIBLE
        campaignViewModel.getCampaign()
        deleteCampaignViewModel.state.observe(::getLifecycle, ::getElimination)
        campaignViewModel.state.observe(::getLifecycle, ::getVaccination)
        campaignFab.setOnClickListener {
            findNavController().navigate(R.id.add_campaign_fragments)
        }

    }

    private fun getElimination(screenState: ScreenState<PostCampaignState>) {
        when (screenState) {
            is ScreenState.Render -> deleteProcessRenderState(screenState.renderState)
        }
    }

    private fun deleteProcessRenderState(renderState: PostCampaignState) {
        when (renderState) {
            is PostCampaignState.ShowSuccess -> {
                Snackbar.make(mroot, "Eliminado correctamente!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.GREEN).show()
                campaignViewModel.getCampaign()
                finish()
            }
            is PostCampaignState.ShowError -> {
            }
        }
    }

    private fun finish() {
        val duration_splash = 3000
        Handler().postDelayed({

            campaignViewModel.getCampaign()

        }, duration_splash.toLong())
    }


    private fun getVaccination(screenState: ScreenState<CampaignState>) {
        when (screenState) {
            is ScreenState.Render -> registerProcessRenderState(screenState.renderState)
        }
    }

    private fun registerProcessRenderState(renderState: CampaignState) {
        when (renderState) {
            is CampaignState.ShowSuccess -> {
                Log.d("josuecitoxd", "ShowSuccess: ${renderState.reg[0].s_nombre}")
                campaignProgressBar.visibility = View.GONE
                vaccinationList(renderState.reg)
            }
            is CampaignState.ShowError -> {

                Log.d("josuecitoxd", "error: " + renderState.reg.message)
            }
        }
    }

    private fun vaccinationList(list: List<CampaignResponseModel>) {
        campaignAdapter = CampaignAdapter(context as Activity, list)
        campaignRecyclerView.adapter = campaignAdapter
        campaignRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        campaignAdapter.setListenerItemSelected(object :
            CampaignAdapter.OnClickSelectedPedidosPendientes {
            override fun onSelectPedidosPendientes(
                id_campana: Int?,
                id_vacuna: Int,
                id_local: Int) {
                val bundle = bundleOf(
                    "id_campana" to id_campana,
                    "id_vacuna" to id_vacuna,
                    "id_local" to id_local
                )

                findNavController().navigate(
                    R.id.action_nav_campaign_to_nav_update_campaign,
                    bundle
                )

            } })

        campaignAdapter.setListenerItemDelete(object :CampaignAdapter.OnClickSelectedDelete{
            override fun onSelectDelete(id: Int?) {
                deleteCampaignViewModel.eliminarCampana(id.toString())
            }

        })


    }


}