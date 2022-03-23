package app.te.protein_chef.presentation.meals.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.make_order.entity.MakeOrderRequest
import app.te.protein_chef.domain.meals.use_case.MealsUseCase
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
  private val mealsUseCase: MealsUseCase,
  val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
  var makeOrderRequest = MakeOrderRequest()
  private val _menuResponse =
    MutableStateFlow<Resource<Any>>(Resource.Default)
  val menuResponse = _menuResponse

  fun getMenuMeals(package_id: Int, selected_date: String, mealTypeId: Int?,
                   meal_type: String? = null) {
    makeOrderRequest.package_type_id = package_id
    makeOrderRequest.selected_date = selected_date
    viewModelScope.launch {
      mealsUseCase.invoke(package_id, selected_date, mealTypeId,meal_type)
        .collect { result ->
          _menuResponse.value = result
        }
    }

  }

}