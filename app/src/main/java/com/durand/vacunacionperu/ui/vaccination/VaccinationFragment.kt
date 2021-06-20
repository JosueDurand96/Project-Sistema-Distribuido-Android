package com.durand.vacunacionperu.ui.vaccination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.durand.vacunacionperu.R

class VaccinationFragment : Fragment() {

    private lateinit var vaccinationViewModel: VaccinationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vaccinationViewModel =
            ViewModelProvider(this).get(VaccinationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_vaccination, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        vaccinationViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}