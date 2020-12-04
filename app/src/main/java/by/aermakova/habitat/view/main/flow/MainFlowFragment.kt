package by.aermakova.habitat.view.main.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentMainBinding

class MainFlowFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        val navController = Navigation.findNavController(view.findViewById(R.id.nav_host_fragment))
        NavigationUI.setupWithNavController(binding.navView, navController)
        return view
    }
}