package app.te.protein_chef.presentation.meals.listeners

interface MealsListener {
  fun changeCategoryType(type: Int)
  fun openItemDetails(meal_id: Int, meal_name: String)
  fun continueOrdering(meal_id: Int, meal_name: String)

}