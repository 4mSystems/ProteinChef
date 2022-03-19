package app.te.protein_chef.presentation.meal_details.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.meals.use_case.MealDetailsUseCase
import app.te.protein_chef.domain.meals.use_case.MealsUseCase
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
  private val mealsUseCase: MealDetailsUseCase,
  val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

  private val _mealDetailsResponse =
    MutableStateFlow<Resource<Any>>(Resource.Default)
  val mealDetailsResponse = _mealDetailsResponse

  fun getMenuMealDetails(meal_id :Int ,meal_type:String) {
    viewModelScope.launch {
      mealsUseCase.invoke(meal_id, meal_type)
        .collect { result ->
          _mealDetailsResponse.value = result
        }
    }

  }

}