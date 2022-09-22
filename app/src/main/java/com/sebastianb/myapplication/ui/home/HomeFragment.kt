package com.sebastianb.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sebastianb.myapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.validateData()
        homeViewModel.userData.observe(viewLifecycleOwner){userData ->
            homeBinding.textView4.text=userData}
        homeViewModel.validateGastoData()
        homeViewModel.gastosList.observe(viewLifecycleOwner){usergastosData ->
            homeBinding.textViewgastostotales.text=usergastosData}



        homeBinding.agregarGastosButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToAgregarGastoFragment())
        }
        homeBinding.estadisticasButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationEstadisticas())
        }
        homeBinding.gastosRecientesButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationGastosrecientes())
        }

        return homeBinding.root
    }
}

