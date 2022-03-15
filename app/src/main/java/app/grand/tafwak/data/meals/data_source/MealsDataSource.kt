package app.grand.tafwak.data.meals.data_source

import app.grand.tafwak.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class MealsDataSource @Inject constructor(private val apiService: MealsServices) :
  BaseRemoteDataSource() {

  suspend fun getMeals(
    package_type_price_id: Int,
    selected_date: String,
    meal_type_id: Int?
  ) = safeApiCall {
    apiService.getMeals(package_type_price_id, selected_date, meal_type_id)
  }
}