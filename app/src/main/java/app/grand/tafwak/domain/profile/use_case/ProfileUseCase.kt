package app.grand.tafwak.domain.profile.use_case

import android.util.Log
import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.profile.entity.UpdateProfileRequest
import app.grand.tafwak.domain.profile.repository.ProfileRepository
import app.grand.tafwak.domain.utils.*
import app.grand.tafwak.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ProfileUseCase @Inject constructor(
  private val profileRepository: ProfileRepository,
  private val userLocalUseCase: UserLocalUseCase
) {

  operator fun invoke(
    request: UpdateProfileRequest
  ): Flow<Resource<BaseResponse<UserResponse>>> = flow {
    if (checkValidation(request)) {
      emit(Resource.Loading)
      val result = profileRepository.updateProfile(request)
      if (result is Resource.Success) {
        userLocalUseCase.saveUserToken(result.value.data.token)
        userLocalUseCase.invoke(result.value.data)
      }
      emit(result)
    }
  }.flowOn(Dispatchers.IO)

  private fun checkValidation(request: UpdateProfileRequest): Boolean {
    var isValid = true
    if (request.name.isEmpty()) {
      request.validation.nameError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.gender.isEmpty()) {
      request.validation.genderError.set(Constants.EMPTY)
      isValid = false
    }

    if (request.age.isEmpty()) {
      request.validation.ageError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.weight.isEmpty() || request.weight == "0.0") {
      request.validation.weightError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.height.isEmpty() || request.weight == "0.0") {
      request.validation.heightError.set(Constants.EMPTY)
      isValid = false
    }

    if (request.isCompleted == 1 && request.phone.isEmpty()) {
      request.validation.phoneError.set(Constants.EMPTY)
      isValid = false
    }
    return isValid
  }
}