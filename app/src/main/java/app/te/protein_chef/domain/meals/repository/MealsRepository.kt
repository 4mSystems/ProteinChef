package app.te.protein_chef.domain.meals.repository

import app.te.protein_chef.domain.meals.entity.MealsMainData
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface MealsRepository {
  suspend fun getMeals(
    package_type_price_id: Int,
    selected_date: String,
    meal_type_id: Int?
  ): Resource<BaseResponse<MealsMainData>>
}