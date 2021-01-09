package by.aermakova.habitat.view.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.BR
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment(), HasSupportFragmentInjector {

    abstract val layoutId: Int
    protected lateinit var binding: VB

    @Inject
    protected lateinit var viewModel: VM

    open var bindingViewModelId: Int = BR.viewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        bindViewModel(binding, viewModel)
    }

    private fun bindViewModel(binding: VB, viewModel: VM) {
        binding.setVariable(bindingViewModelId, viewModel)
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    protected fun hideKeyboard() {
        val activity = requireActivity()
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity.currentFocus ?: View(activity)
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showToastMessage(message: Int) {
        Toast.makeText(requireContext(), resources.getString(message), Toast.LENGTH_SHORT).show()
    }

    protected fun showSnackBarMessage(message: Int) {
        Snackbar.make(requireView(), resources.getString(message), Snackbar.LENGTH_LONG).show()
    }

    protected fun navigateFragment(action: Int) {
        Navigation.findNavController(requireActivity(),
                requireActivity().findViewById<View>(R.id.app_host_fragment).id)
                .navigate(action)
    }

    protected fun backNavigation() {
        hideKeyboard()
        Navigation.findNavController(requireActivity(),
                requireActivity().findViewById<View>(R.id.app_host_fragment).id)
                .popBackStack()
    }

    private var fragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    @Inject
    fun injectDependencies(fragmentInjector: DispatchingAndroidInjector<Fragment>) {
        this.fragmentInjector = fragmentInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentInjector
    }
}