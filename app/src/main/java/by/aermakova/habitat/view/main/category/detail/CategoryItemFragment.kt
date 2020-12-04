package by.aermakova.habitat.view.main.category.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
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

class CategoryItemFragment : BaseFragment() {
    private lateinit var mBinding: FragmentCategoryItemBinding
    private lateinit var mViewModel: CategoryItemViewModel
    private var mCategory: Category? = null
    private var mHabitAdapter: HabitListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_item, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCategory = requireArguments().getParcelable(BUNDLE_TAG)
        setViewSettings(mCategory)
        mViewModel = CategoryItemViewModel(mCategory!!.id)
        setHabitsRecycler()
        mBinding.back.setOnClickListener { backNavigation() }
        mBinding.fabNewHabit.setOnClickListener { createNewHabit() }
    }

    private fun setViewSettings(category: Category?) {
        mBinding.category = category
        val color: CardColor = CardColor.getColorById(category!!.color)
        mBinding.headBackground.setBackgroundColor(ContextCompat.getColor(requireContext(), color.cardColorId))
        mBinding.categoryTitle.setTextColor(ContextCompat.getColor(requireContext(), color.textColorId))
    }

    private fun createNewHabit() {
        val args = Bundle()
        args.putParcelable(BUNDLE_TAG, mCategory)
        Navigation.findNavController(requireActivity(), requireActivity().findViewById<View>(R.id.app_host_fragment).id)
                .navigate(R.id.action_categoryItemFragment_to_addNewHabitFragment, args)
    }

    private fun setHabitsRecycler() {
        mBinding.habitRecycler.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        mBinding.habitRecycler.addItemDecoration(ItemOffsetDecoration(8))
        mHabitAdapter = HabitListAdapter()
        mBinding.habitRecycler.adapter = mHabitAdapter
        observeHabits()
    }

    private fun observeHabits() {
        mViewModel.habitsList?.observe(viewLifecycleOwner,
                Observer { habits: List<Habit?>? -> mHabitAdapter!!.setHabits(habits) })
    }

    companion object {
        private const val BUNDLE_TAG = "category"
    }
}