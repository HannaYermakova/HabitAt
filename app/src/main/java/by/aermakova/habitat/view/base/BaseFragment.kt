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
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import by.aermakova.habitat.R
import by.aermakova.habitat.view.main.category.addNew.AddNewCategoryViewModel
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB: ViewDataBinding> : Fragment() {

    abstract val layoutId: Int
    protected lateinit var binding: VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }


    protected fun hideKeyboard() {
        val activity: Activity = requireActivity()
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
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
}