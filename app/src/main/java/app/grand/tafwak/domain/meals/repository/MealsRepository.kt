package app.grand.tafwak.domain.meals.repository

import app.grand.tafwak.domain.meals.entity.MealsMainData
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource

interface MealsRepository {
  suspend fun getMeals(
    package_type_price_id: Int,
    selected_date: String,
    meal_type_id: Int?
  ): Resource<BaseResponse<MealsMainData>>
}