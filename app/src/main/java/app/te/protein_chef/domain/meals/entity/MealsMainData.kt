package app.te.protein_chef.domain.meals.entity

import app.te.protein_chef.domain.packages_categories.entity.category_menu.CategoryMenu
import androidx.annotation.Keep
import app.te.protein_chef.domain.packages_categories.entity.PackageTypesPrice

@Keep
data class MealsMainData(
    val meals: List<MainMealType>,
    val main_meal_types: MutableList<CategoryMenu>,
    val package_price_Data: PackageTypesPrice
)