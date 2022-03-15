package app.grand.tafwak.data.package_categories.repository

import app.grand.tafwak.data.package_categories.data_source.PackageCategoriesDataSource
import app.grand.tafwak.domain.packages_categories.entity.PackageCategoriesMainData
import app.grand.tafwak.domain.packages_categories.entity.category_menu.CategoryMenu
import app.grand.tafwak.domain.packages_categories.repository.PackageCategoriesRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import javax.inject.Inject

class PackageCategoriesRepositoryImpl @Inject constructor(
  private val remoteDataSource: PackageCategoriesDataSource
) : PackageCategoriesRepository {


  override suspend fun getPackageCategories(package_id: Int): Resource<BaseResponse<PackageCategoriesMainData>> =
    remoteDataSource.getPackageCategories(package_id)

  override suspend fun getCategoryMenu(category_id: Int): Resource<BaseResponse<List<CategoryMenu>>> =
    remoteDataSource.getCategoryMenu(category_id)

}