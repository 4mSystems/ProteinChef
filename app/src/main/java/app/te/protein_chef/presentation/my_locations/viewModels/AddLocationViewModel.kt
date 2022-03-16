package app.te.protein_chef.presentation.my_locations.viewModels

import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.my_locations.entity.MyLocationDto
import app.te.protein_chef.domain.my_locations.entity.request.AddLocationRequest
import app.te.protein_chef.domain.my_locations.use_case.AddLocationUseCase
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
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