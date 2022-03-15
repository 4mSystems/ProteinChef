package app.grand.tafwak.presentation.auth.forgot_password

import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.auth.entity.request.ForgetPasswordRequest
import app.grand.tafwak.domain.auth.use_case.ForgetPasswordUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
  private val forgetPasswordUseCase: ForgetPasswordUseCase
) :
  BaseViewModel() {
  var request = ForgetPasswordRequest()
  private val _forgetPasswordResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val forgetPasswordResponse = _forgetPasswordResponse

  fun sendCode() {
    forgetPasswordUseCase(request)
      .onEach { result ->
        _forgetPasswordResponse.value = result
      }
      .launchIn(viewModelScope)
  }
}