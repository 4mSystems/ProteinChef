package app.grand.tafwak.data.package_categories.data_source

import app.grand.tafwak.domain.packages_categories.entity.PackageCategoriesMainData
import app.grand.tafwak.domain.packages_categories.entity.category_menu.CategoryMenu
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.*

interface PackageCategoriesServices {

  @GET("V1/user/package_types/{package_id}")
  suspend fun getPackageCategories(@Path("package_id") package_id: Int): BaseResponse<PackageCategoriesMainData>

  @GET("V1/user/package_meal_types/main/{category_id}")
  suspend fun getCategoryMenu(@Path("category_id") package_id: Int): BaseResponse<List<CategoryMenu>>

}