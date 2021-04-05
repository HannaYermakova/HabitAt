package by.aermakova.habitat.view.main.category.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.FragmentCategoryItemBinding
import by.aermakova.habitat.model.db.entity.Category
import by.aermakova.habitat.model.db.entity.Habit
import by.aermakova.habitat.model.utilenums.CardColor
import by.aermakova.habitat.view.base.BaseFragment
import by.aermakova.habitat.view.custom.dataadapter.HabitListAdapter
import by.aermakova.habitat.view.custom.layoutmanager.ItemOffsetDecoration


class CategoryItemFragment : BaseFragment<FragmentCategoryItemBinding, CategoryItemViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_category_item

    private var category: Category? = null
    private val habitAdapter by lazy { HabitListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category = requireArguments().getParcelable(BUNDLE_TAG)
        setViewSettings(category)
        setHabitsRecycler()
        binding.back.setOnClickListener { backNavigation() }
        binding.fabNewHabit.setOnClickListener { createNewHabit() }
    }

    private fun setViewSettings(category: Category?) {
        binding.category = category
        val color: CardColor = CardColor.getColorById(category!!.color)
        binding.headBackground.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                color.cardColorId
            )
        )
        binding.categoryTitle.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                color.textColorId
            )
        )
    }

    private fun createNewHabit() {
        val args = Bundle()
        args.putParcelable(BUNDLE_TAG, category)
        Navigation.findNavController(
            requireActivity(),
            requireActivity().findViewById<View>(R.id.app_host_fragment).id
        )
            .navigate(R.id.action_categoryItemFragment_to_addNewHabitFragment, args)
    }

    private fun setHabitsRecycler() {
        with(binding.habitRecycler){
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(ItemOffsetDecoration(8))
            adapter = habitAdapter
        }
        observeHabits()
    }

    private fun observeHabits() {
        viewModel.habitsList?.observe(viewLifecycleOwner,
            Observer { habits: List<Habit?>? -> habitAdapter.setHabits(habits) })
    }

    companion object {
        private const val BUNDLE_TAG = "category"
    }
}