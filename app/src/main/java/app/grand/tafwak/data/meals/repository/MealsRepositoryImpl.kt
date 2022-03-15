package app.grand.tafwak.data.meals.repository

import app.grand.tafwak.data.meals.data_source.MealsDataSource
import app.grand.tafwak.domain.meals.entity.MealsMainData
import app.grand.tafwak.domain.meals.repository.MealsRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(private val remoteDataSource: MealsDataSource) :
  MealsRepository {

  override suspend fun getMeals(
    package_type_price_id: Int,
    selected_date: String,
    meal_type_id: Int?
  ): Resource<BaseResponse<MealsMainData>> =
    remoteDataSource.getMeals(package_type_price_id, selected_date, meal_type_id)
}