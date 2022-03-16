package app.te.protein_chef.presentation.meals

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.domain.utils.Resource
import com.structure.base_mvvm.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.presentation.meals.adapters.MainMealsCategoriesAdapter
import app.te.protein_chef.presentation.meals.adapters.MealsAdapter
import app.te.protein_chef.presentation.meals.listeners.MealsListener
import app.te.protein_chef.presentation.meals.ui_state.MainMealsUiState
import app.te.protein_chef.presentation.meals.viewModels.MealsViewModel
import com.structure.base_mvvm.databinding.FragmentMealsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MealsFragment : BaseFragment<FragmentMealsBinding>(),
  MealsListener {
  private val viewModel: MealsViewModel by viewModels()
  private val adapter = MainMealsCategoriesAdapter()
  private val mealsAdapter = MealsAdapter(this)

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
    // update main category adapter
    adapter.differ.submitList(mainMealsUiState.categoryMenuUiItemList)
    binding.rcMainMeals.setUpAdapter(adapter, "1", "2")
//  update Meals
    mealsAdapter.differ.submitList(mainMealsUiState.mealsUiStateList)
    binding.rcMeals.setUpAdapter(mealsAdapter, "1", "1")
  }


}