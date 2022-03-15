package app.grand.tafwak.presentation.my_locations.viewModels

import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.my_locations.entity.MyLocationDto
import app.grand.tafwak.domain.my_locations.entity.request.AddLocationRequest
import app.grand.tafwak.domain.my_locations.use_case.AddLocationUseCase
import app.grand.tafwak.domain.my_locations.use_case.MyLocationsUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AddLocationViewModel @Inject constructor(
  private val addLocationUseCase: AddLocationUseCase
) : BaseViewModel() {
  val addLocationRequest = AddLocationRequest()
  private val _addLocationResponse =
    MutableStateFlow<Resource<BaseResponse<MyLocationDto>>>(Resource.Default)
  val addLocationResponse = _addLocationResponse


  fun addLocation() {
    addLocationUseCase(addLocationRequest)
      .onEach { result ->
        _addLocationResponse.value = result
      }
      .launchIn(viewModelScope)

  }

}