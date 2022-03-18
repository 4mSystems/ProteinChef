package app.te.protein_chef.domain.meals.use_case

import android.util.Log
import app.te.protein_chef.domain.meals.repository.MealsRepository
import app.te.protein_chef.domain.packages_categories.entity.category_menu.CategoryMenu
import app.te.protein_chef.domain.utils.*
import app.te.protein_chef.presentation.base.extensions.toJsonString
import app.te.protein_chef.presentation.meals.ui_state.MainMealsUiState
import app.te.protein_chef.presentation.meals.ui_state.MealsDataUiState
import app.te.protein_chef.presentation.meals.ui_state.MealsDateUiState
import app.te.protein_chef.presentation.meals.ui_state.MealsUiState
import app.te.protein_chef.presentation.packages.ui_state.CategoryMenuUiItem
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
    val mainMealsUiState = MainMealsUiState()
    if (result is Resource.Success) {
      val data = result.value.data.meals
      // Main Meals
      mainMealsUiState.categoryMenuUiItemList = getMainMeals(result.value.data.main_meal_types)
      if (data.isNullOrEmpty()) {
//        items.add(MyLocationsEmptyUiState())
        emit(Resource.Success(items))
      } else {
        data.groupBy { mainMealType -> mainMealType.date }
          .forEach { meals ->
            //AddDateItem
            MealsDateUiState(meals.key)
              .let { mealsDateUiState ->
                mealsDateUiState.listMeals.addAll(meals.value.map { MealsDataUiState(it) })
                items.add(mealsDateUiState)
              }
            // AddData to list
//            items.addAll(meals.value.map { MealsDataUiState(meals) })
          }
        Log.e("invoke", "invoke: " + items.toJsonString())
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
          title = menu.title,
          image = menu.image
        )
      )
    }
    return items
  }
}