package com.durand.vacunacionperu.ui.campaign.add

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.ui.vaccination.VaccinationAdapter


class LocalVacunacionPopupAdapter(
    private val activity: Activity,
    private val listLine: List<LocalVaccinationResponseModel>
) :
    RecyclerView.Adapter<LocalVacunacionPopupAdapter.LineViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        return LineViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_on_favorites,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listLine.size
    }

    private var mOnClickSelectedPedidosPendientes: OnClickSelectedPedidosPendientes? = null


    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {

        holder.nameTextView.text = listLine[position].s_nombre

        holder.lineIdLinearLayout.setOnClickListener {
            mOnClickSelectedPedidosPendientes?.onSelectPedidosPendientes(
                listLine[position].id_local!!,
                listLine[position].s_nombre!!
            )
        }
    }

    class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var lineIdLinearLayout:LinearLayout = itemView.findViewById(R.id.lineIdLinearLayout)
    }

    fun setListenerItemSelectedLocal(setOnClickSelectedPedidosPendientes: OnClickSelectedPedidosPendientes) {
        mOnClickSelectedPedidosPendientes = setOnClickSelectedPedidosPendientes
    }

    interface OnClickSelectedPedidosPendientes {
        fun onSelectPedidosPendientes(codigo: Int, name: String)
    }

}