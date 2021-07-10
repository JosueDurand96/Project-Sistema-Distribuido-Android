package com.durand.vacunacionperu.ui.vaccination.add

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.durand.domain.request.VaccinationRequest
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.util.ScreenState
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


class AddVaccinationFragment : BaseFragment() {

    private lateinit var cantidadDiasDosisTextInputEditText: TextInputEditText
    private lateinit var cantidadDosisTextInputEditText: TextInputEditText
    private lateinit var fabricaEditText: TextInputEditText
    private lateinit var nameEditText: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var addVaccinationViewModel: PostVaccinationViewModel
    private lateinit var mRoot: ConstraintLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addVaccinationViewModel = ViewModelProvider(this).get(PostVaccinationViewModel::class.java)
        val root = inflater.inflate(R.layout.add_vaccination_fragment, container, false)
        addVaccinationViewModel.state.observe(::getLifecycle, ::getVaccination)
        cantidadDiasDosisTextInputEditText =
            root.findViewById(R.id.cantidadDiasDosisTextInputEditText)
        cantidadDosisTextInputEditText = root.findViewById(R.id.cantidadDosisTextInputEditText)
        fabricaEditText = root.findViewById(R.id.fabricaEditText)
        nameEditText = root.findViewById(R.id.nameEditText)
        loginButton = root.findViewById(R.id.loginButton)

        mRoot = root.findViewById(R.id.addVaccinationConstraintLayout)

        loginButton.setOnClickListener {
            if (nameEditText.text.toString().isEmpty() or fabricaEditText.text.toString()
                    .isEmpty() or cantidadDosisTextInputEditText.text.toString()
                    .isEmpty() or cantidadDiasDosisTextInputEditText.text.toString().isEmpty()
            ) {
                Snackbar.make(
                    mRoot,
                    "Por favor ningun campo debe estar vacío!",
                    Snackbar.LENGTH_SHORT
                ).setBackgroundTint(Color.RED).show()

            } else {
                addVaccinationViewModel.postVaccination(
                    VaccinationRequest(
                        nameEditText.text.toString(),
                        fabricaEditText.text.toString(),
                        cantidadDosisTextInputEditText.text.toString().toInt(),
                        cantidadDiasDosisTextInputEditText.text.toString().toInt()
                    )
                )
            }

        }

        return root
    }

    private fun getVaccination(screenState: ScreenState<AddVaccinationState>) {
        when (screenState) {
            is ScreenState.Render -> registerProcessRenderState(screenState.renderState)
        }
    }

    private fun registerProcessRenderState(renderState: AddVaccinationState) {
        when (renderState) {
            is AddVaccinationState.ShowSuccess -> {
                Snackbar.make(mRoot, "Agregado correctament!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.GREEN).show()
                finish()
            }
            is AddVaccinationState.ShowError -> {

            }
        }
    }


    private fun finish() {
        val duration_splash = 1500
        Handler().postDelayed({

            findNavController().navigate(R.id.nav_vaccination)

        }, duration_splash.toLong())

    }

}