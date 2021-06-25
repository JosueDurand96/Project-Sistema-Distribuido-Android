package com.durand.vacunacionperu.ui.vaccination.update

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.durand.domain.request.PutVaccinationRequest
import com.durand.domain.request.VaccinationRequest
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.ui.vaccination.add.AddVaccinationState
import com.durand.vacunacionperu.ui.vaccination.add.AddVaccinationViewModel
import com.durand.vacunacionperu.util.ScreenState
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class UpdateVaccinationFragment : BaseFragment() {

    private lateinit var cantidadDiasDosisTextInputEditText: TextInputEditText
    private lateinit var cantidadDosisTextInputEditText: TextInputEditText
    private lateinit var fabricaEditText: TextInputEditText
    private lateinit var nameEditText: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var mRoot: ConstraintLayout
    private lateinit var updateVaccinationViewModel: UpdateVaccinationViewModel
    private var idVacuna: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateVaccinationViewModel =
            ViewModelProvider(this).get(UpdateVaccinationViewModel::class.java)
        val root = inflater.inflate(R.layout.update_vaccination_fragment, container, false)
        updateVaccinationViewModel.state.observe(::getLifecycle, ::getVaccination)
        cantidadDiasDosisTextInputEditText = root.findViewById(R.id.updatecantidadDiasDosisTextInputEditText)
        cantidadDosisTextInputEditText = root.findViewById(R.id.updatecantidadDosisTextInputEditText)
        fabricaEditText = root.findViewById(R.id.updatefabricaEditText)
        nameEditText = root.findViewById(R.id.updatenameEditText)

        loginButton = root.findViewById(R.id.updateloginButton)

        mRoot = root.findViewById(R.id.updateaddVaccinationConstraintLayout)

        idVacuna = arguments?.getInt("id")!!
        val name = arguments?.getString("name")!!
        val fabrica = arguments?.getString("fabrica")!!
        val cantidadDosis = arguments?.getInt("cantidadDosis")!!
        val cantidadDias = arguments?.getInt("cantidadDias")!!


        nameEditText.setText(name)
        fabricaEditText.setText(fabrica)
        cantidadDosisTextInputEditText.setText(cantidadDosis.toString())
        cantidadDiasDosisTextInputEditText.setText(cantidadDias.toString())

        Log.d("idVacuna", "idVacuna.toString()")
        Log.d("idVacuna", idVacuna.toString())
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
                updateVaccinationViewModel.postVaccination(
                    PutVaccinationRequest(
                        idVacuna,
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
                Snackbar.make(mRoot, "Actualizado correctament!", Snackbar.LENGTH_SHORT)
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