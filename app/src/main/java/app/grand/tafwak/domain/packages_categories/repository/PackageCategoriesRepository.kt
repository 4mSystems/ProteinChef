package app.grand.tafwak.domain.packages_categories.repository

import app.grand.tafwak.domain.packages_categories.entity.PackageCategoriesMainData
import app.grand.tafwak.domain.packages_categories.entity.category_menu.CategoryMenu
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource

interface PackageCategoriesRepository {
  suspend fun getPackageCategories(package_id: Int): Resource<BaseResponse<PackageCategoriesMainData>>
  suspend fun getCategoryMenu(category_id: Int): Resource<BaseResponse<List<CategoryMenu>>>
}