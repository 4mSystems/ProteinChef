package app.te.protein_chef.domain.meals.use_case

import app.te.protein_chef.domain.meals.repository.MealsRepository
import app.te.protein_chef.domain.utils.*
import app.te.protein_chef.presentation.meal_details.ui_state.MealDetailsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MealDetailsUseCase @Inject constructor(
  private val mealsRepository: MealsRepository
) {

  operator fun invoke(
    meal_id: Int,
    meal_type: String
  ) = flow {

    emit(Resource.Loading)
    val result = mealsRepository.getMealDetails(meal_id)
    if (result is Resource.Success) {
      val data = result.value.data
      emit(Resource.Success(MealDetailsUiState(data,meal_type)))
    } else
      emit(result)
  }.flowOn(Dispatchers.IO)

}