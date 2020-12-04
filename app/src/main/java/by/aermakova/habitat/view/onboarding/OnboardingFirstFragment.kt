package by.aermakova.habitat.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentOnboarding1Binding

class OnboardingFirstFragment : Fragment() {
    private lateinit var mBinding: FragmentOnboarding1Binding
    private var mView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentOnboarding1Binding.inflate(inflater, container, false)
        mView = mBinding.root
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.continueButton.setOnClickListener { Navigation.findNavController(mView!!).navigate(R.id.action_welcomeFirstFragment_to_welcomeSecondFragment) }
        mBinding.cancelButton.setOnClickListener { Navigation.findNavController(mView!!).navigate(R.id.action_welcomeFirstFragment_to_registrationFragment) }
    }
}