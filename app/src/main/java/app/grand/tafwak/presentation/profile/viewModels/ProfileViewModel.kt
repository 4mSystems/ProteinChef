package app.grand.tafwak.presentation.profile.viewModels

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.profile.entity.UpdateProfileRequest
import app.grand.tafwak.domain.profile.use_case.ProfileUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseViewModel
import app.grand.tafwak.presentation.profile.ui_state.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
  private val userLocalUseCase: UserLocalUseCase,
  private val profileUseCase: ProfileUseCase
) :
  BaseViewModel() {
  @Bindable
  val request = UpdateProfileRequest()
  private val _profileResponse =
    MutableStateFlow<Resource<BaseResponse<UserResponse>>>(Resource.Default)
  val profileResponse = _profileResponse
  val userUiState = UserUiState(null)

  init {
    viewModelScope.launch {

      userLocalUseCase.invoke().collect { userLocal ->
        userUiState.userResponse = userLocal
        Log.e("ProfileViewModel", ":"+userLocal.age )
        request.isCompleted = userLocal.isCompleted
        request.name = userLocal.name
        request.phone = userLocal.phone
        request.age = userLocal.age
        request.gender = userLocal.gender
        request.weight = userLocal.weight.toString()
        request.height = userLocal.height.toString()
      }
    }

  }

  fun updateProfile() {
    profileUseCase.invoke(request).catch { exception ->
      Log.e(
        "updateProfile",
        "updateProfile: ${exception.printStackTrace()}"
      )
    }.onEach { result ->
      _profileResponse.value = result
    }.launchIn(viewModelScope)
  }


}