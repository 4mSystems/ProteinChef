package app.te.protein_chef.presentation.additions_meals

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentMealAdditionsBinding
import app.te.protein_chef.domain.make_order.entity.OrderAdditions
import app.te.protein_chef.domain.make_order.entity.SelectedMeals
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.presentation.base.utils.Constants
import app.te.protein_chef.presentation.meals.adapters.MainMealsCategoriesAdapter
import app.te.protein_chef.presentation.meals.adapters.MealsAdapter
import app.te.protein_chef.presentation.meals.listeners.MealsListener
import app.te.protein_chef.presentation.meals.ui_state.MainMealsUiState
import app.te.protein_chef.presentation.meals.ui_state.MealsDateUiState
import app.te.protein_chef.presentation.meals.ui_state.MealsUiState
import app.te.protein_chef.presentation.meals.viewModels.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class AdditionsMealsFragment : BaseFragment<FragmentMealAdditionsBinding>(),
  MealsListener {
  private val viewModel: MealsViewModel by viewModels()
  private val adapter = MainMealsCategoriesAdapter(this)
  private val mealsAdapter = MealsAdapter(null, this)
  private val listOfMeals = mutableListOf<MutableList<MealsUiState>>()
  private val listSelectedOfMeals = mutableListOf<MutableList<MealsDateUiState>>()

  override
  fun getLayoutId() = R.layout.fragment_meal_additions

  override fun setBindingVariables() {
    binding.eventListener = this
    viewModel.makeOrderRequest =
      AdditionsMealsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).orderRequest
    getMeals(null)
  }

  private fun getMeals(mealTypeId: Int?) {
    viewModel.getMenuMeals(
      viewModel.makeOrderRequest.package_type_id,
      viewModel.makeOrderRequest.selected_date,
      mealTypeId,
      "sub"
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
    if (adapter.differ.currentList.size == 0) {
      adapter.differ.submitList(mainMealsUiState.categoryMenuUiItemList)
      binding.rcMainMeals.setUpAdapter(adapter, "1", "2")
      mainMealsUiState.categoryMenuUiItemList.forEachIndexed { index, categoryMenuUiItem ->
        listSelectedOfMeals.add(index, mutableListOf())
      }
    }
    //  update Meals
    listOfMeals.add(mainMealsUiState.mealsUiStateList)
    listSelectedOfMeals[adapter.lastPosition] =
      listOfMeals[adapter.lastPosition] as MutableList<MealsDateUiState>
    updateMealsAdapter(mainMealsUiState.mealsUiStateList)
  }

  private fun updateMealsAdapter(mealsUiStateList: MutableList<MealsUiState>) {
    mealsAdapter.differ.submitList(mealsUiStateList)
    binding.rcMeals.setUpAdapter(mealsAdapter, "1", "1")
  }

  override fun changeCategoryType(type: Int) {
    if (adapter.currentPosition > adapter.differ.currentList.size - 1 && type == Constants.FORWARD) {
      listSelectedOfMeals[adapter.lastPosition] =
        mealsAdapter.differ.currentList as MutableList<MealsDateUiState>
      continueOrdering(0, "")
    } else {
      adapter.changeSelected(type)
      if (listOfMeals.size < adapter.currentPosition) {
        getMeals(adapter.lastSelected)
      } else {
        listSelectedOfMeals[adapter.lastPosition] =
          listOfMeals[adapter.lastPosition] as MutableList<MealsDateUiState>
        updateMealsAdapter(listOfMeals[adapter.lastPosition])
      }
    }
  }

  override fun openItemDetails(meal_id: Int, meal_name: String) {}

  override fun continueOrdering(meal_id: Int, meal_name: String) {
    listSelectedOfMeals.forEach { mealsDateUiState ->
      mealsDateUiState.forEach { mealsData ->
        mealsData.listMeals.forEach { mealsDataUiState ->
          if (mealsDataUiState.getMealSelected()) {
            viewModel.makeOrderRequest.selected_meal.add(
              SelectedMeals(
                meal_id = mealsDataUiState.getId(),
                date = mealsData.getDate(),
                meal_type_id = mealsData.typeId
              )
            )
          }
        }
      }
    }
    viewModel.makeOrderRequest.meals_additional_total = 0.0 // clear before adding
    adapter.differ.currentList.map { menuType ->
      if (menuType.selected == 1) {
        viewModel.makeOrderRequest.meals_additional_total += menuType.price
        viewModel.makeOrderRequest.order_additions.add(
          OrderAdditions(
            price = menuType.price,
            meal_type_id = menuType.meal_type_id
          )
        )
      }
    }
    navigateSafe(
      AdditionsMealsFragmentDirections.actionAdditionsMealsFragmentToPrivacyOrderFragment(
        viewModel.makeOrderRequest
      )
    )
  }

}