package app.te.protein_chef.data.meals.data_source

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class MealsDataSource @Inject constructor(private val apiService: MealsServices) :
  BaseRemoteDataSource() {

  suspend fun getMeals(
    package_type_price_id: Int,
    selected_date: String,
    meal_type_id: Int?,
    meal_type: String? = null
  ) = safeApiCall {
    apiService.getMeals(package_type_price_id, selected_date, meal_type_id,meal_type)
  }

  suspend fun getMealDetails(
    meal_id: Int
  ) = safeApiCall {
    apiService.getMealDetails(meal_id)
  }

}