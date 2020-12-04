package by.aermakova.habitat.view.base

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

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