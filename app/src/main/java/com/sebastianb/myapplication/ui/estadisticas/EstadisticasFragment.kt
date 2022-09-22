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
import com.google.firestore.v1.Value
import com.sebastianb.myapplication.R
import com.sebastianb.myapplication.databinding.FragmentEstadisticasBinding


class EstadisticasFragment : Fragment() {

    private lateinit var estadisticasBinding: FragmentEstadisticasBinding
    private lateinit var estadisticasViewModel: EstadisticasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var alimentacion=0.0f
        var indumentaria=0.0f
        var cuidadopersonal=0.0f
        var entretenimiento=0.0f
        var otros=0.0f
        estadisticasBinding = FragmentEstadisticasBinding.inflate(inflater, container, false)
        estadisticasViewModel = ViewModelProvider(this)[EstadisticasViewModel::class.java]
        estadisticasViewModel.Alimentacion()
        estadisticasViewModel.userAData.observe(viewLifecycleOwner){userAData ->
            alimentacion= userAData!!.toFloat()
            setupPieChart(alimentacion,indumentaria,cuidadopersonal,entretenimiento,otros)}
        estadisticasViewModel.Indumentaria()
        estadisticasViewModel.userIData.observe(viewLifecycleOwner){userIData ->
            indumentaria= userIData!!.toFloat()
            setupPieChart(alimentacion,indumentaria,cuidadopersonal,entretenimiento,otros)}
        estadisticasViewModel.Cuidadopersonal()
        estadisticasViewModel.userPData.observe(viewLifecycleOwner){userPData ->
            cuidadopersonal= userPData!!.toFloat()
            setupPieChart(alimentacion,indumentaria,cuidadopersonal,entretenimiento,otros)}
        estadisticasViewModel.Entretenimiento()
        estadisticasViewModel.userEData.observe(viewLifecycleOwner){userEData ->
            entretenimiento= userEData!!.toFloat()
            setupPieChart(alimentacion,indumentaria,cuidadopersonal,entretenimiento,otros)}
        estadisticasViewModel.Otros()
        estadisticasViewModel.userOData.observe(viewLifecycleOwner){userOData ->
            otros= userOData!!.toFloat()
            setupPieChart(alimentacion,indumentaria,cuidadopersonal,entretenimiento,otros)}



        return estadisticasBinding.root
    }

    private fun setupPieChart(alimentacion:Float,indumentaria:Float,cuidadopersonal:Float,entretenimiento:Float,otros:Float) {
        val pieEntries = arrayListOf<PieEntry>()
        pieEntries.add(PieEntry(alimentacion,"Alimentacion"))
        pieEntries.add(PieEntry(indumentaria,"Indumentaria"))
        pieEntries.add(PieEntry(cuidadopersonal,"Cuidado personal"))
        pieEntries.add(PieEntry(entretenimiento,"Entretenimiento"))
        pieEntries.add(PieEntry(otros,"otros"))



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