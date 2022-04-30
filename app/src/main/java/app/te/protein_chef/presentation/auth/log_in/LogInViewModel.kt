package app.te.protein_chef.presentation.auth.log_in

import android.util.Log
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.auth.entity.request.LogInRequest
import app.te.protein_chef.domain.auth.entity.request.SocialLogInRequest
import app.te.protein_chef.domain.auth.use_case.LogInUseCase
import app.te.protein_chef.domain.auth.use_case.SocialLogInUseCase
import app.te.protein_chef.domain.profile.entity.UpdateProfileRequest
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
  private val logInUseCase: LogInUseCase,
  private val socialLogInUseCase: SocialLogInUseCase,
  val userLocalUseCase: UserLocalUseCase
) : BaseViewModel() {

  var request = LogInRequest()
  var socialLogInRequest = SocialLogInRequest()
  var registerRequest = UpdateProfileRequest()
  private val _logInResponse =
    MutableStateFlow<Resource<BaseResponse<UserResponse>>>(Resource.Default)
  val logInResponse = _logInResponse

  init {
    updateFireBaseToken()
  }

  fun onLogInClicked() {
    logInUseCase(request)
      .catch { exception ->
        Log.e(
          "onLogInClicked",
          "onLogInClicked: ${exception.printStackTrace()}"
        )
      }
      .onEach { result ->
        _logInResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun socialLogin(socialType: String) {
    socialLogInRequest.email = registerRequest.email
    socialLogInRequest.social_id = registerRequest.socialToken
    socialLogInRequest.social_type = socialType
    socialLogInRequest.device_token = request.device_token
    socialLogInUseCase(socialLogInRequest)
      .catch { exception ->
        Log.e(
          "onLogInClicked",
          "onLogInClicked: ${exception.printStackTrace()}"
        )
      }
      .onEach { result ->
        _logInResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  private fun updateFireBaseToken() {
    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
      if (!task.isSuccessful) {
        return@OnCompleteListener
      }
      // Get new FCM registration token
      request.device_token = task.result
    })
  }

}