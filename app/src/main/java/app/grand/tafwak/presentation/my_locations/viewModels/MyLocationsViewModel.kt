package app.grand.tafwak.presentation.my_locations.viewModels

import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.my_locations.use_case.MyLocationsUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.my_locations.ui_state.MyLocationsDataUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyLocationsViewModel @Inject constructor(
  private val myLocationsUseCase: MyLocationsUseCase,
  private val userLocalUseCase: UserLocalUseCase
) : BaseViewModel() {

  private val _locationsResponse =
    MutableStateFlow<Resource<Any>>(Resource.Default)
  val locationsResponse = _locationsResponse
 private val _removeLocationResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val removeLocationResponse = _removeLocationResponse

  init {
    getMyCoupons()
  }

   fun getMyCoupons() {
    myLocationsUseCase.invoke()
      .onEach { result ->
        _locationsResponse.value = result
      }
      .launchIn(viewModelScope)

  }
 fun removeLocation(locationId:Int) {
    myLocationsUseCase.removeLocation(locationId)
      .onEach { result ->
        _removeLocationResponse.value = result
      }
      .launchIn(viewModelScope)

  }

  fun setDefaultLocation(myLocationsDataUiState: MyLocationsDataUiState) {
    viewModelScope.launch {
      userLocalUseCase.saveDefaultLocation(myLocationsDataUiState)
    }
  }
}