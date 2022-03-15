package app.grand.tafwak.domain.meals.use_case

import android.util.Log
import app.grand.tafwak.domain.meals.repository.MealsRepository
import app.grand.tafwak.domain.packages_categories.entity.category_menu.CategoryMenu
import app.grand.tafwak.domain.utils.*
import app.grand.tafwak.presentation.meals.ui_state.MealsDataUiState
import app.grand.tafwak.presentation.meals.ui_state.MealsDateUiState
import app.grand.tafwak.presentation.meals.ui_state.MealsUiState
import app.grand.tafwak.presentation.packages.ui_state.CategoryMenuUiItem
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
    meal_type_id: Int?
  ) = flow {

    emit(Resource.Loading)
    val result = mealsRepository.getMeals(package_type_price_id, selected_date, meal_type_id)
    val items = mutableListOf<MealsUiState>()
    if (result is Resource.Success) {
      val data = result.value.data.meals
      // Main Meals
      emit(Resource.Success(getMainMeals(result.value.data.main_meal_types)))

      if (data.isNullOrEmpty()) {
//        items.add(MyLocationsEmptyUiState())
        emit(Resource.Success(items))
      } else {
        data.groupBy { mainMealType -> mainMealType.date }
          .forEach {
            //AddDateItem
            MealsDateUiState(it.key, it.key)
              .let { mealsDateUiState -> items.add(mealsDateUiState) }
            // AddData to list
            items.addAll(it.value.map { MealsDataUiState(it) })
          }

        emit(Resource.Success(items))
      }

    } else
      emit(result)
  }.flowOn(Dispatchers.IO)

  private fun getMainMeals(mainMeals: List<CategoryMenu>): MutableList<CategoryMenuUiItem> {
    val items = mutableListOf<CategoryMenuUiItem>()
    mainMeals.map { menu ->
      items.add(
        CategoryMenuUiItem(
          id = menu.id,
          title = menu.title,
          image = menu.image
        )
      )
    }
    return items
  }
}