package com.durand.vacunacionperu.ui.vaccination.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.durand.vacunacionperu.R
import com.google.android.material.textfield.TextInputEditText

class AddVaccinationFragment : Fragment() {
    private lateinit var cantidadDiasDosisTextInputEditText: TextInputEditText
    private lateinit var cantidadDosisTextInputEditText: TextInputEditText
    private lateinit var fabricaEditText: TextInputEditText
    private lateinit var nameEditText: TextInputEditText

    private lateinit var addVaccinationViewModel: AddVaccinationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_vaccination_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addVaccinationViewModel = ViewModelProvider(this).get(AddVaccinationViewModel::class.java)

    }

}