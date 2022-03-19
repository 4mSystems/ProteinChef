package app.te.protein_chef.presentation.meal_details

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentMealDetailsBinding
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.presentation.meal_details.ui_state.MealDetailsUiState
import app.te.protein_chef.presentation.meal_details.viewModels.MealDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MealDetailsFragment : BaseFragment<FragmentMealDetailsBinding>() {
  private val viewModel: MealDetailsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_meal_details
  override fun setBindingVariables() {
    viewModel.getMenuMealDetails(
      MealDetailsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).mealId,
      MealDetailsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).title
    )
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.mealDetailsResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            val mealDetailsUiState: MealDetailsUiState = it.value as MealDetailsUiState
            binding.detailsUiState = mealDetailsUiState
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }
}