package app.te.protein_chef.domain.meals.use_case

import app.te.protein_chef.domain.meals.repository.MealsRepository
import app.te.protein_chef.domain.packages_categories.entity.PackageTypesPrice
import app.te.protein_chef.domain.packages_categories.entity.category_menu.CategoryMenu
import app.te.protein_chef.domain.utils.*
import app.te.protein_chef.presentation.meals.ui_state.MainMealsUiState
import app.te.protein_chef.presentation.meals.ui_state.MealsDataUiState
import app.te.protein_chef.presentation.meals.ui_state.MealsDateUiState
import app.te.protein_chef.presentation.meals.ui_state.MealsUiState
import app.te.protein_chef.presentation.packages.ui_state.CategoryMenuUiItem
import app.te.protein_chef.presentation.packages.ui_state.PackageCategoryUiItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MealsUseCase @Inject constructor(
  private val mealsRepository: MealsRepository
) {

  operator fun invoke(
    package_type_price_id: Int,
    selected_date: String,
    meal_type_id: Int?,
    meal_type: String?
  ) = flow {

    emit(Resource.Loading)
    val result =
      mealsRepository.getMeals(package_type_price_id, selected_date, meal_type_id, meal_type)
    val items = mutableListOf<MealsUiState>()
    val mainMealsUiState = MainMealsUiState()
    if (result is Resource.Success) {
      val data = result.value.data.meals
      // Main Meals
      mainMealsUiState.categoryMenuUiItemList = getMainMeals(result.value.data.main_meal_types)
      mainMealsUiState.mealTypeUiState = getPackageDetails(result.value.data.package_price_Data)
      if (data.isNullOrEmpty()) {
        emit(Resource.Success(items))
      } else {
        data.groupBy { mainMealType -> mainMealType.date }
          .forEach { meals ->
            //AddDateItem
            MealsDateUiState(meals.key, mainMealsUiState.mealTypeUiState.id)
              .let { mealsDateUiState ->
                mealsDateUiState.listMeals.addAll(meals.value.map { MealsDataUiState(it) })
                items.add(mealsDateUiState)
              }
          }
        mainMealsUiState.mealsUiStateList = items
      }
      emit(Resource.Success(mainMealsUiState))
    } else
      emit(result)
  }.flowOn(Dispatchers.IO)

  private fun getMainMeals(mainMeals: MutableList<CategoryMenu>): MutableList<CategoryMenuUiItem> {
    val items = mutableListOf<CategoryMenuUiItem>()
    mainMeals.map { menu ->
      items.add(
        CategoryMenuUiItem(
          id = menu.id,
          meal_type_id = menu.meal_type_id,
          title = menu.title,
          image = menu.image,
          price = menu.price
        )
      )
    }
    return items
  }

  private fun getPackageDetails(package_details: PackageTypesPrice): PackageCategoryUiItem {
    return PackageCategoryUiItem(
      id = package_details.id,
      title = package_details.title,
      packageDays = package_details.days_count,
      price = package_details.price
    )
  }
}