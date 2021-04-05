package by.aermakova.habitat.view.main.dashboard

import android.os.Bundle
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentDashboardBinding
import by.aermakova.habitat.view.base.BaseFragment

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_dashboard

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeUseCase(viewModel.habitsObserver)
        observeUseCase(viewModel.categoryObserver)
    }
}