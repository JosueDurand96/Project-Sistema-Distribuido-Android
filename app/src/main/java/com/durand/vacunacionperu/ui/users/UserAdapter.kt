package com.durand.vacunacionperu.ui.users

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel
import com.durand.domain.model.user.UserResponseModel
import com.durand.vacunacionperu.R


class UserAdapter(
    private val activity: Activity,
    private val listLine: List<UserResponseModel>
) :
    RecyclerView.Adapter<UserAdapter.LineViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        return LineViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
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
        holder.phoneTextView.text = listLine[position].s_num_celular
        holder.lastNameTextView.text = listLine[position].s_apellidos
        holder.nameTextView.text = listLine[position].s_nombres
//        holder.buttonConstraintLayout.setOnClickListener {
//            mOnClickSelectedPedidosPendientes?.onSelectPedidosPendientes(
//                    listLine[position].id,
//                    listLine[position].title!!
//            )
//        }

    }

    class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)
        var phoneTextView: TextView = itemView.findViewById(R.id.phoneTextView)
    }

    fun setListenerItemSelected(setOnClickSelectedPedidosPendientes: OnClickSelectedPedidosPendientes) {
        mOnClickSelectedPedidosPendientes = setOnClickSelectedPedidosPendientes
    }

    interface OnClickSelectedPedidosPendientes {
        fun onSelectPedidosPendientes(id: Int?, name: String)
    }

}