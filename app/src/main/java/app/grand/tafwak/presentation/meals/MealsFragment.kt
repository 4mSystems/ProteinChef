package app.grand.tafwak.presentation.meals

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.handleApiError
import app.grand.tafwak.presentation.base.extensions.hideKeyboard
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import app.grand.tafwak.presentation.base.extensions.setUpAdapter
import app.grand.tafwak.presentation.meals.adapters.MainMealsCategoriesAdapter
import app.grand.tafwak.presentation.meals.ui_state.MealsUiState
import app.grand.tafwak.presentation.meals.viewModels.MealsViewModel
import app.grand.tafwak.presentation.packages.PackageCategoriesFragmentArgs
import app.grand.tafwak.presentation.packages.PackageCategoriesFragmentDirections
import app.grand.tafwak.presentation.packages.ui_state.CategoryMenuUiItem
import app.grand.tafwak.presentation.packages.ui_state.PackageCategoriesEventListener
import app.grand.tafwak.presentation.packages.ui_state.PackageCategoryUiItem
import com.structure.base_mvvm.databinding.FragmentMealsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MealsFragment : BaseFragment<FragmentMealsBinding>(),
  PackageCategoriesEventListener {
  private val viewModel: MealsViewModel by viewModels()
  private val adapter = MainMealsCategoriesAdapter()

  override
  fun getLayoutId() = R.layout.fragment_meals
  override fun setBindingVariables() {
//    binding.eventListener = this
    viewModel.getMenuMeals(
      MealsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).packageId,
      MealsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).selectedDate,
      null
    )
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
            val list: List<CategoryMenuUiItem> = it.value as List<CategoryMenuUiItem>
            adapter.differ.submitList(list)
            binding.rcMainMeals.setUpAdapter(adapter, "1", "2")
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
  }


  override fun openDates() {
    if (adapter.lastSelected != -1)
      navigateSafe(
        PackageCategoriesFragmentDirections.actionPackageCategoriesFragmentToMenuDialog(
          adapter.lastSelected,
          PackageCategoriesFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).title
        )
      )
  }

}