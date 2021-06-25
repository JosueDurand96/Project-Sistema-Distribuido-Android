package com.durand.vacunacionperu.ui.vaccination

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.ui.vaccination.add.AddVaccinationState
import com.durand.vacunacionperu.util.ScreenState
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_vaccination.*


class VaccinationFragment : BaseFragment() {

    private lateinit var vaccinationViewModel: VaccinationViewModel
    private lateinit var vaccinationAdapter: VaccinationAdapter
    private lateinit var vaccinationRecyclerView: RecyclerView
    private lateinit var vaccinationFab: FloatingActionButton
    private lateinit var deleteVaccinationViewModel: DeleteVaccinationViewModel
    var mroot: CoordinatorLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vaccinationViewModel = ViewModelProvider(this).get(VaccinationViewModel::class.java)
        deleteVaccinationViewModel =
            ViewModelProvider(this).get(DeleteVaccinationViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_vaccination, container, false)
        vaccinationRecyclerView = root.findViewById(R.id.vaccinationRecyclerView)
        vaccinationFab = root.findViewById(R.id.vaccinationFab)
        vaccinationFab.setOnClickListener {
            findNavController().navigate(R.id.action_nav_vaccination_to_nav_vaccinwation)
        }
        mroot = root.findViewById(R.id.mroot)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGetVaccination()
        vaccinationViewModel.state.observe(::getLifecycle, ::getVaccination)
        deleteVaccinationViewModel.state.observe(::getLifecycle, ::deleteVaccination)
    }

    private fun deleteVaccination(screenState: ScreenState<AddVaccinationState>) {
        when (screenState) {
            is ScreenState.Render -> deleteProcessRenderState(screenState.renderState)
        }
    }

    private fun deleteProcessRenderState(renderState: AddVaccinationState) {
        when (renderState) {
            is AddVaccinationState.ShowSuccess -> {

                Snackbar.make(mroot!!, "Eliminado correctament!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.BLUE).show()
                finish()
            }
            is AddVaccinationState.ShowError -> {

            }
        }
    }

    private fun finish() {

        val duration_splash = 1500
        Handler().postDelayed({

            vaccinationViewModel.getVaccination()

        }, duration_splash.toLong())
    }

    private fun initGetVaccination() {
        vaccinationProgressBar.visibility = View.VISIBLE
        vaccinationViewModel.getVaccination()
    }


    private fun getVaccination(screenState: ScreenState<VaccinationState>) {
        when (screenState) {
            is ScreenState.Render -> registerProcessRenderState(screenState.renderState)
        }
    }

    private fun registerProcessRenderState(renderState: VaccinationState) {
        when (renderState) {
            is VaccinationState.ShowSuccess -> {
                vaccinationProgressBar.visibility = View.GONE
                vaccinationList(renderState.reg)

            }
            is VaccinationState.ShowError -> {

            }
        }
    }

    private fun vaccinationList(list: List<VaccinationResponseModel>) {
        vaccinationAdapter = VaccinationAdapter(context as Activity, list)
        vaccinationRecyclerView.adapter = vaccinationAdapter
        vaccinationRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        vaccinationAdapter.setListenerItemSelected(object :
            VaccinationAdapter.OnClickSelectedPedidosPendientes {
            override fun onSelectPedidosPendientes(
                id: Int?,
                name: String,
                fabrica: String,
                cantidadDosis: Int,
                cantidadDias: Int
            ) {

                val bundle = bundleOf(
                    "id" to id,
                    "name" to name,
                    "fabrica" to fabrica,
                    "cantidadDosis" to cantidadDosis,
                    "cantidadDias" to cantidadDias
                )
                findNavController().navigate(
                    R.id.action_nav_vaccination_to_nav_vacci_update,
                    bundle
                )
            }
        })

        vaccinationAdapter.setListenerItemDelete(object :
            VaccinationAdapter.OnClickSelectedDelete {
            override fun onSelectDelete(id: Int?) {
                deleteVaccinationViewModel.postVaccination(id.toString())
            }

        })

    }
}