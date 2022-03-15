package app.grand.tafwak.domain.meals.entity

import app.grand.tafwak.domain.packages_categories.entity.category_menu.CategoryMenu

data class MealsMainData(
    val meals: List<MainMealType>,
    val main_meal_types: List<CategoryMenu>
)