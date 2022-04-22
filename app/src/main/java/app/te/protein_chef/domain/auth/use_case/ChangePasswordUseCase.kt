package app.te.protein_chef.domain.auth.use_case

import app.te.protein_chef.domain.auth.entity.request.*
import app.te.protein_chef.domain.auth.enums.AuthFieldsValidation
import app.te.protein_chef.domain.auth.repository.AuthRepository
import app.te.protein_chef.domain.profile.entity.UpdatePassword
import app.te.protein_chef.domain.utils.*
import app.te.protein_chef.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ChangePasswordUseCase @Inject constructor(
  private val authRepository: AuthRepository
) {

  @Throws(ChangePasswordValidationException::class)
  operator fun invoke(request: ChangePasswordRequest): Flow<Resource<BaseResponse<*>>> = flow {
    if (request.password.isEmpty()) {
      throw ChangePasswordValidationException(AuthFieldsValidation.EMPTY_PASSWORD.value.toString())
    }

    if (request.password_confirmation.isEmpty()) {
      throw ChangePasswordValidationException(AuthFieldsValidation.EMPTY_CONFIRM_PASSWORD.value.toString())
    }

    emit(Resource.Loading)
    val result = authRepository.changePassword(request)
    emit(result)
  }.flowOn(Dispatchers.IO)

  operator fun invoke(request: UpdatePassword): Flow<Resource<BaseResponse<*>>> = flow {
    if (checkUpdatePasswordValidation(request)) {
      if (request.password_confirmation != request.password) {
        emit(
          Resource.Failure(
            FailureStatus.EMPTY,
            message = Constants.NOT_MATCH_PASSWORD.toString(),
          )
        )
      } else {
        emit(Resource.Loading)
        val result = authRepository.updatePassword(request)
        emit(result)
      }
    }
  }.flowOn(Dispatchers.IO)

  private fun checkUpdatePasswordValidation(request: UpdatePassword): Boolean {
    var isValid = true
    if (!request.isForget && request.old_password.isEmpty()) {
      request.validation.oldPasswordError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.password.isEmpty()) {
      request.validation.newPasswordError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.password_confirmation.isEmpty()) {
      request.validation.newPasswordConfirmError.set(Constants.EMPTY)
      isValid = false
    }
    return isValid
  }
}