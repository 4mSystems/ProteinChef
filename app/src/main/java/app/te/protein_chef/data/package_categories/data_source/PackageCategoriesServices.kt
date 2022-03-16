package app.te.protein_chef.data.package_categories.data_source

import app.te.protein_chef.domain.packages_categories.entity.PackageCategoriesMainData
import app.te.protein_chef.domain.packages_categories.entity.category_menu.CategoryMenu
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.*

interface PackageCategoriesServices {

  @GET("V1/user/package_types/{package_id}")
  suspend fun getPackageCategories(@Path("package_id") package_id: Int): BaseResponse<PackageCategoriesMainData>

  @GET("V1/user/package_meal_types/main/{category_id}")
  suspend fun getCategoryMenu(@Path("category_id") package_id: Int): BaseResponse<List<CategoryMenu>>

}