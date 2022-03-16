package app.te.protein_chef.data.package_categories.data_source

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class PackageCategoriesDataSource @Inject constructor(private val apiService: PackageCategoriesServices) :
  BaseRemoteDataSource() {

  suspend fun getPackageCategories(package_id: Int) = safeApiCall {
    apiService.getPackageCategories(package_id)
  }

  suspend fun getCategoryMenu(category_id: Int) = safeApiCall {
    apiService.getCategoryMenu(category_id)
  }


}