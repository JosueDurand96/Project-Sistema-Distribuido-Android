package com.durand.vacunacionperu.ui.vaccination

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.vacunacionperu.R


class VaccinationAdapter(
    private val activity: Activity,
    private val listLine: List<VaccinationResponseModel>
) :
    RecyclerView.Adapter<VaccinationAdapter.LineViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        return LineViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_vaccination_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listLine.size
    }

    private var mOnClickSelectedPedidosPendientes: OnClickSelectedPedidosPendientes? = null
    private var mOnClickSelectedDelete: OnClickSelectedDelete? = null
    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {

        holder.fabricTextView.text = listLine[position].s_fabricante
        holder.nameTextView.text = listLine[position].s_nombre
        holder.onlickLinearLayout.setOnClickListener {
            mOnClickSelectedPedidosPendientes?.onSelectPedidosPendientes(
                listLine[position].id_vacuna,
                listLine[position].s_nombre!!,
                listLine[position].s_fabricante!!,
                listLine[position].qt_dosis!!,
                listLine[position].qt_dias!!

            )
        }

        holder.imageDeleteImageView.setOnClickListener {
            mOnClickSelectedDelete?.onSelectDelete(
                listLine[position].id_vacuna
            )
        }

    }

    class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var fabricTextView: TextView = itemView.findViewById(R.id.fabricTextView)
        var onlickLinearLayout: ConstraintLayout =
            itemView.findViewById(R.id.onlickConstraintLayout)
        var imageDeleteImageView: ImageView = itemView.findViewById(R.id.imageDeleteImageView)
    }

    fun setListenerItemSelected(setOnClickSelectedPedidosPendientes: OnClickSelectedPedidosPendientes) {
        mOnClickSelectedPedidosPendientes = setOnClickSelectedPedidosPendientes
    }

    fun setListenerItemDelete(setOnClickSelectedDelete: OnClickSelectedDelete) {
        mOnClickSelectedDelete = setOnClickSelectedDelete
    }


    interface OnClickSelectedPedidosPendientes {
        fun onSelectPedidosPendientes(
            id: Int?,
            name: String,
            fabrica: String,
            cantidadDosis: Int,
            cantidadDias: Int
        )
    }

    interface OnClickSelectedDelete {
        fun onSelectDelete(
            id: Int?
        )
    }

}