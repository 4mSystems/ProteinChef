package app.te.protein_chef.data.package_categories.data_source

import app.te.protein_chef.domain.packages_categories.entity.PackageCategoriesMainData
import app.te.protein_chef.domain.packages_categories.entity.category_menu.CategoryMenu
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.*

interface PackageCategoriesServices {

  @GET("V1/user/package_types")
  suspend fun getPackageCategories(@Query("package_id") package_id: Int): BaseResponse<PackageCategoriesMainData>

  @GET("V1/user/package_meal_types/main")
  suspend fun getCategoryMenu(@Query("package_price_id") package_id: Int): BaseResponse<List<CategoryMenu>>

  @GET("V1/user/package_parent_type")
  suspend fun getPackageSubCategories(
    @Query("package_type_id") packageTypeId: Int,
    @Query("package_id") package_id: Int
  ): BaseResponse<PackageCategoriesMainData>

}