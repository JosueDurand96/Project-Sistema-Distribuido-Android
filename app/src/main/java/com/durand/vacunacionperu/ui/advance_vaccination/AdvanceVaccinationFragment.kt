package com.durand.vacunacionperu.ui.advance_vaccination

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.durand.vacunacionperu.R

class AdvanceVaccinationFragment : Fragment() {

    companion object {
        fun newInstance() = AdvanceVaccinationFragment()
    }

    private lateinit var viewModel: AdvanceVaccinationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_advance_vaccination, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdvanceVaccinationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}