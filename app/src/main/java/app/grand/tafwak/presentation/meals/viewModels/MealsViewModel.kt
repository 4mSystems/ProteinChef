package app.grand.tafwak.presentation.meals.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.meals.use_case.MealsUseCase
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.packages.ui_state.CategoryMenuUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
  private val mealsUseCase: MealsUseCase,
  val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

  private val _menuResponse =
    MutableStateFlow<Resource<Any>>(Resource.Default)
  val menuResponse = _menuResponse

  fun getMenuMeals(package_id: Int, selected_date: String, mealTypeId: Int?) {
    viewModelScope.launch {
      mealsUseCase.invoke(package_id, selected_date, mealTypeId)
        .collect { result ->
            _menuResponse.value = result
        }
    }

  }

}