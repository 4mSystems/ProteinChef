package app.te.protein_chef.domain.packages_categories.use_case

import app.te.protein_chef.domain.packages_categories.repository.PackageCategoriesRepository
import app.te.protein_chef.domain.utils.*
import app.te.protein_chef.presentation.packages.ui_state.PackageCategoryUiItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class PackageSubCategoriesUseCase @Inject constructor(
  private val packageCategoriesRepository: PackageCategoriesRepository
) {

  operator fun invoke(package_type_id: Int, package_id: Int) = flow {
    emit(Resource.Loading)
    val result = packageCategoriesRepository.getPackageSubCategories(package_type_id, package_id)
    val items = mutableListOf<PackageCategoryUiItem>()
    if (result is Resource.Success) {
      result.value.data.package_types_prices.map { packageType ->
        items.add(
          PackageCategoryUiItem(
            id = packageType.id,
            title = packageType.title,
            packageDays = packageType.days_count,
            price = packageType.price
          )
        )
      }
      emit(Resource.Success(items))

    } else
      emit(result)
  }.flowOn(Dispatchers.IO)

}