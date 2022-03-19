package app.te.protein_chef.data.meals.data_source

import app.te.protein_chef.domain.meals.entity.MealDetails
import app.te.protein_chef.domain.meals.entity.MealsMainData
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealsServices {
  @GET("V1/user/package_menu_meals")
  suspend fun getMeals(
    @Query("package_type_price_id") package_type_price_id: Int,
    @Query("selected_date") selected_date: String,
    @Query("meal_type_id") meal_type_id: Int?,
    @Query("meal_type") meal_type: String?,
  ): BaseResponse<MealsMainData>

  @GET("V1/user/meal/details/{meal_id}")
  suspend fun getMealDetails(
    @Path("meal_id") meal_id: Int?,
  ): BaseResponse<MealDetails>

}