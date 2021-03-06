package app.te.protein_chef.presentation.meals

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.presentation.meals.adapters.MainMealsCategoriesAdapter
import app.te.protein_chef.presentation.meals.adapters.MealsAdapter
import app.te.protein_chef.presentation.meals.listeners.MealsListener
import app.te.protein_chef.presentation.meals.ui_state.MainMealsUiState
import app.te.protein_chef.presentation.meals.ui_state.MealsUiState
import app.te.protein_chef.presentation.meals.viewModels.MealsViewModel
import app.te.protein_chef.databinding.FragmentMealsBinding
import app.te.protein_chef.domain.make_order.entity.SelectedMeals
import app.te.protein_chef.presentation.base.utils.Constants
import app.te.protein_chef.presentation.meals.ui_state.MealsDateUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MealsFragment : BaseFragment<FragmentMealsBinding>(),
  MealsListener {
  val viewModel: MealsViewModel by viewModels()
  private val adapter = MainMealsCategoriesAdapter(this)
  private val mealsAdapter = MealsAdapter(null, this)
  private val listOfMeals = mutableListOf<MutableList<MealsUiState>>()
  private val listSelectedOfMeals = mutableListOf<MutableList<MealsDateUiState>>()

  override
  fun getLayoutId() = R.layout.fragment_meals

  override fun setBindingVariables() {
    binding.eventListener = this
    getMeals(null)
  }

  private fun getMeals(mealTypeId: Int?) {
    viewModel.getMenuMeals(
      MealsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).packageId,
      MealsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).selectedDate,
      mealTypeId
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
            val mainMealsUiState = it.value as MainMealsUiState
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
      binding.mainMeals.rcMainMeals.setUpAdapter(adapter, "1", "2")
      mainMealsUiState.categoryMenuUiItemList.forEachIndexed { index, _ ->
        listSelectedOfMeals.add(index, mutableListOf())
      }
      // update package data
      binding.packageUiState = mainMealsUiState.mealTypeUiState
      viewModel.makeOrderRequest.meals_total =
        mainMealsUiState.mealTypeUiState.price // for total price
    }
    //  update Meals
    listOfMeals.add(mainMealsUiState.mealsUiStateList)
    listSelectedOfMeals[adapter.lastPosition] =
      listOfMeals[adapter.lastPosition] as MutableList<MealsDateUiState>
    updateMealsAdapter(mainMealsUiState.mealsUiStateList)
  }

  private fun updateMealsAdapter(mealsUiStateList: MutableList<MealsUiState>) {
    mealsAdapter.differ.submitList(mealsUiStateList)
    binding.layoutMeals.rcMeals.setUpAdapter(mealsAdapter, "1", "1")
  }

  private fun collectMealsData() {
    viewModel.makeOrderRequest.selected_meal.clear() // clear any meal before adding
    viewModel.makeOrderRequest.meals_additional_total = 0.0 // clear any meals additions Total
    listSelectedOfMeals.forEach { mealsDateUiState ->
      mealsDateUiState.forEach { mealsData ->
        mealsData.listMeals.forEach { mealsDataUiState ->
          if (mealsDataUiState.getMealSelected()) {
            viewModel.makeOrderRequest.selected_meal.add(
              SelectedMeals(
                meal_id = mealsDataUiState.getId(),
                date = mealsData.requestDate(),
                meal_type_id = mealsData.getId()
              )
            )
          }
        }
      }
    }
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

  override fun openItemDetails(meal_id: Int, meal_name: String) {
    navigateSafe(
      MealsFragmentDirections.actionMealsFragmentToMealDetailsFragment(
        meal_id,
        meal_name,
        MealsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).title
      )
    )
  }

  override fun continueOrdering(meal_id: Int, meal_name: String) {
    collectMealsData()
    if (binding.haveSnacks.text == "true")
      navigateSafe(MealsFragmentDirections.actionMealsFragmentToAdditionsDialog(viewModel.makeOrderRequest))
    else
      navigateSafe(MealsFragmentDirections.actionMealsFragmentToPrivacyOrderFragment(viewModel.makeOrderRequest))
  }
}