package app.te.protein_chef.presentation.auth.changePassword

import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.auth.entity.request.ChangePasswordRequest
import app.te.protein_chef.domain.auth.use_case.ChangePasswordUseCase
import app.te.protein_chef.domain.general.use_case.GeneralUseCases
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import app.te.protein_chef.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
  private val changePasswordUseCase: ChangePasswordUseCase,
  private val generalUseCases: GeneralUseCases
) :
  BaseViewModel() {
  var request = ChangePasswordRequest()
  private val _changePasswordResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val changePasswordResponse = _changePasswordResponse
  var validationException = SingleLiveEvent<Int>()

  var isLogged: Boolean = false

  init {
    viewModelScope.launch {
      generalUseCases.checkLoggedInUserUseCase().collect {
//        isLogged = it
//        notifyPropertyChanged(BR.isLogged)
      }
    }
  }

  fun changePassword() {
    changePasswordUseCase(request)
      .catch { exception -> validationException.value = exception.message?.toInt() }
      .onEach { result ->
        _changePasswordResponse.value = result
      }
      .launchIn(viewModelScope)
  }


}