package by.aermakova.habitat.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentOnboarding3Binding

class OnboardingThirdFragment : Fragment() {
    private lateinit var mBinding: FragmentOnboarding3Binding
    private lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentOnboarding3Binding.inflate(inflater, container, false)
        mView =  mBinding.root
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.continueButton.setOnClickListener { Navigation.findNavController(mView).navigate(R.id.action_welcomeThirdFragment_to_registrationFlowFragment) }
        mBinding.back.setOnClickListener { Navigation.findNavController(mView).popBackStack() }
    }
}