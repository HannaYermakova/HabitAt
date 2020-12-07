package by.aermakova.habitat.view.onboarding.signup

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentSignUpBinding
import by.aermakova.habitat.model.Preferences
import by.aermakova.habitat.view.base.BaseFragment

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    private lateinit var mBinding: FragmentSignUpBinding

    private lateinit var mViewModel: SignUpViewModel
    private lateinit var mView: View

    override val layoutId: Int
        get() = R.layout.fragment_sign_up

/*    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        mView = mBinding.root
        mViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        mBinding.viewModel = mViewModel
        return mView
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToNavigationChanged(mViewModel)
        mBinding.back.setOnClickListener { Navigation.findNavController(mView).popBackStack() }
    }

    private fun subscribeToNavigationChanged(viewModel: SignUpViewModel?) {
        viewModel?.saveUserNameCommand?.observe(viewLifecycleOwner, Observer { name: String? -> saveUserName(name) })
        viewModel?.showErrorMessageCommand?.observe(viewLifecycleOwner, Observer { message: Int? -> showSnackBarMessage(message!!) })
    }

    private fun saveUserName(name: String?) {
        hideKeyboard()
        Preferences.instance?.setUserName(requireContext(), name)
        Navigation.findNavController(requireActivity(),
                requireActivity().findViewById<View>(R.id.app_host_fragment).id)
                .navigate(R.id.action_welcomeFlowFragment_to_mainFlowFragment)
    }
}