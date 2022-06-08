package app.te.protein_chef.domain.packages_categories.repository

import app.te.protein_chef.domain.packages_categories.entity.PackageCategoriesMainData
import app.te.protein_chef.domain.packages_categories.entity.category_menu.CategoryMenu
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface PackageCategoriesRepository {
  suspend fun getPackageCategories(package_id: Int): Resource<BaseResponse<PackageCategoriesMainData>>
  suspend fun getPackageSubCategories(package_type_id: Int, package_id: Int): Resource<BaseResponse<PackageCategoriesMainData>>
  suspend fun getCategoryMenu(category_id: Int): Resource<BaseResponse<List<CategoryMenu>>>
}