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

    private var mCategory: Category? = null
    private var mHabitAdapter: HabitListAdapter? = null

    override val layoutId: Int
        get() = R.layout.fragment_category_item


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCategory = requireArguments().getParcelable(BUNDLE_TAG)
        setViewSettings(mCategory)
        setHabitsRecycler()
        binding.back.setOnClickListener { backNavigation() }
        binding.fabNewHabit.setOnClickListener { createNewHabit() }
    }

    private fun setViewSettings(category: Category?) {
        binding.category = category
        val color: CardColor = CardColor.getColorById(category!!.color)
        binding.headBackground.setBackgroundColor(ContextCompat.getColor(requireContext(), color.cardColorId))
        binding.categoryTitle.setTextColor(ContextCompat.getColor(requireContext(), color.textColorId))
    }

    private fun createNewHabit() {
        val args = Bundle()
        args.putParcelable(BUNDLE_TAG, mCategory)
        Navigation.findNavController(requireActivity(), requireActivity().findViewById<View>(R.id.app_host_fragment).id)
                .navigate(R.id.action_categoryItemFragment_to_addNewHabitFragment, args)
    }

    private fun setHabitsRecycler() {
        binding.habitRecycler.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.habitRecycler.addItemDecoration(ItemOffsetDecoration(8))
        mHabitAdapter = HabitListAdapter()
        binding.habitRecycler.adapter = mHabitAdapter
        observeHabits()
    }

    private fun observeHabits() {
        viewModel.habitsList?.observe(viewLifecycleOwner,
                Observer { habits: List<Habit?>? -> mHabitAdapter!!.setHabits(habits) })
    }

    companion object {
        private const val BUNDLE_TAG = "category"
    }
}