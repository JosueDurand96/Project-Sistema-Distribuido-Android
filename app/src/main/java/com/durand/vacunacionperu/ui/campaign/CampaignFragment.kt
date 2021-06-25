package com.durand.vacunacionperu.ui.campaign

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.ui.vaccination.VaccinationAdapter
import com.durand.vacunacionperu.ui.vaccination.VaccinationState
import com.durand.vacunacionperu.ui.vaccination.VaccinationViewModel
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.android.synthetic.main.fragment_campaign.*
import kotlinx.android.synthetic.main.fragment_vaccination.*
import kotlinx.android.synthetic.main.fragment_vaccination.vaccinationProgressBar

class CampaignFragment : BaseFragment() {

    private lateinit var campaignViewModel: CampaignViewModel
    private lateinit var campaignAdapter: CampaignAdapter
    private lateinit var campaignRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        campaignViewModel = ViewModelProvider(this).get(CampaignViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_campaign, container, false)
        campaignRecyclerView = root.findViewById(R.id.campaignRecyclerView)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGetVaccination()
        campaignProgressBar.visibility = View.VISIBLE
        campaignViewModel.getCampaign()
        campaignViewModel.state.observe(::getLifecycle, ::getVaccination)
    }

    private fun initGetVaccination() {

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
    }

}