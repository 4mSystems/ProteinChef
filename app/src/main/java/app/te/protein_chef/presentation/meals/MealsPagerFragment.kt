package app.te.protein_chef.presentation.meals

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentMealsPagerBinding
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.presentation.meals.adapters.ViewPagerStateAdapter
import app.te.protein_chef.presentation.meals.ui_state.MainMealsUiState
import app.te.protein_chef.presentation.meals.viewModels.MealsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MealsPagerFragment : BaseFragment<FragmentMealsPagerBinding>() {
  private val viewModel: MealsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_meals_pager

  override fun setBindingVariables() {
    getMeals(null)
  }

  private fun getMeals(mealTypeId: Int?) {
//    viewModel.getMenuMeals(
//      MealsPagerFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).packageId,
//      MealsPagerFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).selectedDate,
//      mealTypeId
//    )
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.menuResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            val mainMealsUiState: MainMealsUiState = it.value as MainMealsUiState
            bindUi(mainMealsUiState)

          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }

  private fun bindUi(mainMealsUiState: MainMealsUiState) {

    binding.packageUiState = mainMealsUiState.mealTypeUiState
    binding.pager.let { viewPager2 ->
      val viewPagerStateAdapter = ViewPagerStateAdapter(this)
      mainMealsUiState.categoryMenuUiItemList.forEachIndexed lit@{ index, categoryMenuUiItem ->
//        if (index == 0)
//          viewPagerStateAdapter.addItem(MealsFragment(mainMealsUiState.mealsUiStateList))
//        else
//          viewPagerStateAdapter.addItem(MealsFragment(mutableListOf()))
      }

      viewPager2.adapter = viewPagerStateAdapter
      TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
        tab.text = mainMealsUiState.categoryMenuUiItemList[position].title
        tab.id = mainMealsUiState.categoryMenuUiItemList[position].meal_type_id

//        val tabMargin: View = (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(position)
//        val p: ViewGroup.MarginLayoutParams = tabMargin.layoutParams as ViewGroup.MarginLayoutParams
//        p.setMargins(8, 0, 8, 0)
//        tabMargin.requestLayout()
      }.attach()
    }
    for (i in 0 until binding.tabLayout.tabCount) {
      val tab = (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
      val p = tab.layoutParams as ViewGroup.MarginLayoutParams
      p.setMargins(8, 0, 8, 0)
      tab.requestLayout()
    }
    binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
      override fun onTabSelected(tab: TabLayout.Tab) {

      }

      override fun onTabUnselected(tab: TabLayout.Tab) {
        Log.e("onTabUnselected", "onTabUnselected: "+tab.id)
      }
      override fun onTabReselected(tab: TabLayout.Tab) {
        Log.e("onTabReselected", "onTabReselected: " + tab.id)
      }
    })
  }

}