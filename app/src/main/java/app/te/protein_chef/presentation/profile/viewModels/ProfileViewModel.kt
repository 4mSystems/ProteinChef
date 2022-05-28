package app.te.protein_chef.presentation.profile.viewModels

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.profile.entity.UpdateProfileRequest
import app.te.protein_chef.domain.profile.use_case.ProfileUseCase
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import app.te.protein_chef.presentation.profile.ui_state.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
  private val userLocalUseCase: UserLocalUseCase,
  private val profileUseCase: ProfileUseCase,
  val savedStateHandle: SavedStateHandle
) :
  BaseViewModel() {
  @Bindable
   var request = UpdateProfileRequest()
  private val _profileResponse =
    MutableStateFlow<Resource<BaseResponse<UserResponse>>>(Resource.Default)
  val profileResponse = _profileResponse
  val userUiState = UserUiState(null, "")

  init {
    viewModelScope.launch {
      savedStateHandle.get<UpdateProfileRequest>("register_info")?.let { registerInfo ->
        request = registerInfo
      }

      userLocalUseCase.invoke().collect { userLocal ->
        userUiState.socialToken = request.socialToken
        userUiState.userResponse = userLocal
        request.isCompleted = userLocal.isCompleted
        request.name = request.name.ifEmpty { userLocal.name }
        request.phone = userLocal.phone
        request.age = if (userLocal.age != "0") userLocal.age else ""
        request.gender = userLocal.gender
        request.weight = if (userLocal.weight != 0F) userLocal.weight.toString() else ""
        request.height = if (userLocal.height != 0F) userLocal.height.toString() else ""
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