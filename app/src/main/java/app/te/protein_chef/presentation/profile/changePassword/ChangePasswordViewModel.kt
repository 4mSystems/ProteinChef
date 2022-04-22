package app.te.protein_chef.presentation.profile.changePassword

import android.util.Log
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.domain.auth.use_case.ChangePasswordUseCase
import app.te.protein_chef.domain.profile.entity.UpdatePassword
import app.te.protein_chef.domain.profile.entity.UpdateProfileRequest
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
  private val changePasswordUseCase: ChangePasswordUseCase,
  val userLocalUseCase: UserLocalUseCase
) : BaseViewModel() {
  var request = UpdatePassword()
  private val _changePasswordResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val changePasswordResponse = _changePasswordResponse
  fun changePassword() {
    changePasswordUseCase.invoke(request)
      .catch { exception -> Log.e("changePassword", "changePassword: " + exception.message) }
      .onEach { result ->
        _changePasswordResponse.value = result
      }
      .launchIn(viewModelScope)
  }

}