package app.te.protein_chef.presentation.auth.confirmCode

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.auth.entity.request.ForgetPasswordRequest
import app.te.protein_chef.domain.auth.entity.request.VerifyAccountRequest
import app.te.protein_chef.domain.auth.use_case.ForgetPasswordUseCase
import app.te.protein_chef.domain.auth.use_case.VerifyAccountUseCase
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(
  private val verifyAccountUseCase: VerifyAccountUseCase,
  private val forgetPasswordUseCase: ForgetPasswordUseCase,
  savedStateHandle: SavedStateHandle
) :
  BaseViewModel() {
  val request = VerifyAccountRequest()
  private val forgetPasswordRequest = ForgetPasswordRequest()

  private val _verifyResponse =
    MutableStateFlow<Resource<BaseResponse<UserResponse>>>(Resource.Default)
  val verifyResponse = _verifyResponse

  private val _forgetResponse = MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val forgetResponse = _forgetResponse

  init {
    savedStateHandle.get<String>("phone")?.let { email ->
      request.phone = email
      forgetPasswordRequest.phone = email
    }
    savedStateHandle.get<String>("type")?.let { type ->
      request.type = type
    }

  }

  fun verifyAccount() {
    verifyAccountUseCase(request)
      .onEach { result ->
        _verifyResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun resendCode() {
    forgetPasswordRequest.type = request.type
    forgetPasswordUseCase(forgetPasswordRequest)
      .onEach { result ->
        _forgetResponse.value = result
      }
      .launchIn(viewModelScope)
  }

}