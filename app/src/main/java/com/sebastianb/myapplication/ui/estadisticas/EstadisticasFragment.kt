package com.sebastianb.myapplication.ui.estadisticas


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.sebastianb.myapplication.R
import com.sebastianb.myapplication.databinding.FragmentEstadisticasBinding


class EstadisticasFragment : Fragment() {

    private lateinit var estadisticasBinding: FragmentEstadisticasBinding
    private lateinit var estadisticasViewModel: EstadisticasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        estadisticasBinding = FragmentEstadisticasBinding.inflate(inflater, container, false)
        estadisticasViewModel = ViewModelProvider(this)[EstadisticasViewModel::class.java]
        setupPieChart()

        return estadisticasBinding.root
    }

    private fun setupPieChart() {
        val pieEntries = arrayListOf<PieEntry>()
        pieEntries.add(PieEntry(30.0f,"Alimentacion"))
        pieEntries.add(PieEntry(80.0f,"Indumentaria"))
        pieEntries.add(PieEntry(20.0f,"Cuidado personal"))
        pieEntries.add(PieEntry(38.0f,"Entretenimiento"))
        pieEntries.add(PieEntry(65.0f,"otros"))



        estadisticasBinding.pieChart.animateXY(1000, 1000)
        val pieDataSet = PieDataSet(pieEntries,null)

        pieDataSet.setColors(
            resources.getColor(R.color.fondo),
            resources.getColor(R.color.black),
            resources.getColor(R.color.teal_200),
            resources.getColor(R.color.teal_700),
            resources.getColor(R.color.purple_200)
        )


        val pieData = PieData(pieDataSet)


        pieData.setDrawValues(true)

        estadisticasBinding.pieChart.description.isEnabled=false
        
        estadisticasBinding.pieChart.data = pieData

    }


}