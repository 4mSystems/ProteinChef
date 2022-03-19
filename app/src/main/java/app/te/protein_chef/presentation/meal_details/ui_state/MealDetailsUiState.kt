package app.te.protein_chef.presentation.meal_details.ui_state

import app.te.protein_chef.domain.meals.entity.MealDetails

data class MealDetailsUiState(val mealDetails: MealDetails, val mealType: String) {
  fun getMealImage(): String = mealDetails.image
  fun getMealTitle(): String = mealDetails.title
  fun getMealBody(): String = mealDetails.body
}