package by.aermakova.habitat.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import by.aermakova.habitat.databinding.FragmentOnboardingBinding

class OnboardingFlowFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mBinding = FragmentOnboardingBinding.inflate(inflater, container, false)
        val view = mBinding.root
        Navigation.setViewNavController(view, NavHostFragment.findNavController(this))
        return view
    }
}