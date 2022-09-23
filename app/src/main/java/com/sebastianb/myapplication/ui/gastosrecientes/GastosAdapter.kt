package com.sebastianb.myapplication.ui.gastosrecientes


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.sebastianb.myapplication.R
import com.sebastianb.myapplication.data.GastoRepository
import com.sebastianb.myapplication.databinding.CardViewGastoItemBinding
import com.sebastianb.myapplication.databinding.FragmentGastosrecientesBinding
import com.sebastianb.myapplication.model.Gasto
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope


class GastosAdapter(
    private val gastoList: ArrayList<Gasto>
) : RecyclerView.Adapter<GastosAdapter.GastoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GastoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_gasto_item, parent, false)
        return GastoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GastoViewHolder, position: Int) {
        val gasto = gastoList[position]
        holder.bind(gasto)


    }

    override fun getItemCount(): Int = gastoList.size

    fun appendItems(newList:ArrayList<Gasto>){
        gastoList.clear()
        gastoList.addAll(newList)
        notifyDataSetChanged()
    }

    class GastoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewGastoItemBinding.bind(itemView)

        fun bind(gasto: Gasto) {

            with(binding) {
                descripcionTextView.text = "Descripción: "+gasto.description
                categoriaTextView.text = "Categoria: "+gasto.category
                establecimientoTextView.text = "Establecimiento: "+gasto.establishment
                valorTextView.text = "valor: $"+gasto.amount.toString()
                fechaTextView.text = gasto.date



               /* if (gasto.category.toString() == "Alimentación") {
                    Picasso.get().load(R.mipmap.ic_alimentacion).into(imageView)
                } else if (gasto.category.toString() == "Indumentaria") {
                    Picasso.get().load(R.mipmap.ic_indumentaria).into(imageView)
                } else if (gasto.category.toString() == "Entretenimiento") {
                    Picasso.get().load(R.mipmap.ic_entretenimiento).into(imageView)
                } else if (gasto.category.toString() == "Cuidado personal") {
                    Picasso.get().load(R.mipmap.ic_cuidado_personal).into(imageView)
                } else if (gasto.category.toString() == "Otros") {
                    Picasso.get().load(R.mipmap.ic_otros).into(imageView)
                }*/
            }

        }
    }

}