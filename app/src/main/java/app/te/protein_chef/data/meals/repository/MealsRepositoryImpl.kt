package app.te.protein_chef.data.meals.repository

import app.te.protein_chef.data.meals.data_source.MealsDataSource
import app.te.protein_chef.domain.meals.entity.MealDetails
import app.te.protein_chef.domain.meals.entity.MealsMainData
import app.te.protein_chef.domain.meals.repository.MealsRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(private val remoteDataSource: MealsDataSource) :
  MealsRepository {

  override suspend fun getMeals(
    package_type_price_id: Int,
    selected_date: String,
    meal_type_id: Int?,
    meal_type: String?
  ): Resource<BaseResponse<MealsMainData>> =
    remoteDataSource.getMeals(package_type_price_id, selected_date, meal_type_id, meal_type)

  override suspend fun getMealDetails(
    meal_id: Int
  ): Resource<BaseResponse<MealDetails>> =
    remoteDataSource.getMealDetails(meal_id)

}