package com.durand.vacunacionperu.ui.local_vaccination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.durand.vacunacionperu.R

class LocalVaccinationFragment : Fragment() {

    private lateinit var localVaccinationViewModel: LocalVaccinationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        localVaccinationViewModel =
            ViewModelProvider(this).get(LocalVaccinationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_local_vaccination, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        localVaccinationViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}