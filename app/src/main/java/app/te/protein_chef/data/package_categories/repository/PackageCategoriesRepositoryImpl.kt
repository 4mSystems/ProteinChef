package app.te.protein_chef.data.package_categories.repository

import app.te.protein_chef.data.package_categories.data_source.PackageCategoriesDataSource
import app.te.protein_chef.domain.packages_categories.entity.PackageCategoriesMainData
import app.te.protein_chef.domain.packages_categories.entity.category_menu.CategoryMenu
import app.te.protein_chef.domain.packages_categories.repository.PackageCategoriesRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import javax.inject.Inject

class PackageCategoriesRepositoryImpl @Inject constructor(
  private val remoteDataSource: PackageCategoriesDataSource
) : PackageCategoriesRepository {


  override suspend fun getPackageCategories(package_id: Int): Resource<BaseResponse<PackageCategoriesMainData>> =
    remoteDataSource.getPackageCategories(package_id)

  override suspend fun getCategoryMenu(category_id: Int): Resource<BaseResponse<List<CategoryMenu>>> =
    remoteDataSource.getCategoryMenu(category_id)

}