package app.te.protein_chef.domain.meals.repository

import app.te.protein_chef.domain.meals.entity.MealDetails
import app.te.protein_chef.domain.meals.entity.MealsMainData
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface MealsRepository {
  suspend fun getMeals(
    package_type_price_id: Int,
    selected_date: String,
    meal_type_id: Int?,
    meal_type: String? = null
  ): Resource<BaseResponse<MealsMainData>>

  suspend fun getMealDetails(
    meal_id: Int
  ): Resource<BaseResponse<MealDetails>>

}