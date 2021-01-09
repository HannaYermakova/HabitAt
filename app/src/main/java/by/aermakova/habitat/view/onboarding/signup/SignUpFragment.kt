package by.aermakova.habitat.view.onboarding.signup

import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentSignUpBinding
import by.aermakova.habitat.view.base.BaseFragment

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_sign_up
}