package app.grand.tafwak.domain.packages_categories.use_case

import app.grand.tafwak.domain.packages_categories.repository.PackageCategoriesRepository
import app.grand.tafwak.domain.utils.*
import app.grand.tafwak.presentation.packages.ui_state.CategoryMenuUiItem
import app.grand.tafwak.presentation.packages.ui_state.PackageCategoryUiItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class PackageCategoriesUseCase @Inject constructor(
  private val packageCategoriesRepository: PackageCategoriesRepository
) {

  operator fun invoke(package_id: Int) = flow {
    emit(Resource.Loading)
    val result = packageCategoriesRepository.getPackageCategories(package_id)
    val items = mutableListOf<PackageCategoryUiItem>()
    if (result is Resource.Success) {
      result.value.data.package_types_prices.map { packageType ->
        items.add(
          PackageCategoryUiItem(
            id = packageType.id,
            title = packageType.title,
            price = packageType.price
          )
        )
      }
      emit(Resource.Success(items))

    } else
      emit(result)
  }.flowOn(Dispatchers.IO)

  fun getCategoryMenu(category_id: Int) = flow {
    emit(Resource.Loading)
    val result = packageCategoriesRepository.getCategoryMenu(category_id)
    val items = mutableListOf<CategoryMenuUiItem>()
    if (result is Resource.Success) {
      result.value.data.map { menu ->
        items.add(
          CategoryMenuUiItem(
            id = menu.id,
            title = menu.title,
            image = menu.image
          )
        )
      }
      emit(Resource.Success(items))

    } else
      emit(result)
  }.flowOn(Dispatchers.IO)


}