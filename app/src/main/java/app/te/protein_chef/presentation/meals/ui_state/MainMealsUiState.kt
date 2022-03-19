package app.te.protein_chef.presentation.meals.ui_state

import androidx.annotation.Keep
import app.te.protein_chef.presentation.packages.ui_state.CategoryMenuUiItem
import app.te.protein_chef.presentation.packages.ui_state.PackageCategoryUiItem

@Keep
data class MainMealsUiState(
  var categoryMenuUiItemList: MutableList<CategoryMenuUiItem> = mutableListOf(),
  var mealsUiStateList: MutableList<MealsUiState> = mutableListOf(),
  var mealTypeUiState: PackageCategoryUiItem = PackageCategoryUiItem()
)
