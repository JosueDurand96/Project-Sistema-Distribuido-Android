package com.durand.vacunacionperu.ui.campaign.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.durand.vacunacionperu.R

class AddCampaignFragment : Fragment() {

    companion object {
        fun newInstance() = AddCampaignFragment()
    }

    private lateinit var viewModel: AddCampaignViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_campaign_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddCampaignViewModel::class.java)
        // TODO: Use the ViewModel
    }

}