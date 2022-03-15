package app.grand.tafwak.domain.auth.use_case

import app.grand.tafwak.domain.auth.entity.request.RegisterRequest
import app.grand.tafwak.domain.auth.repository.AuthRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.FailureStatus
import app.grand.tafwak.presentation.base.utils.Constants
import app.grand.tafwak.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
  private val authRepository: AuthRepository
) {

  operator fun invoke(request: RegisterRequest): Flow<Resource<BaseResponse<*>>> = flow {
    if (checkValidation(request)) {
      if (request.password_confirmation != request.password) {
        emit(
          Resource.Failure(
            FailureStatus.EMPTY,
            message = Constants.NOT_MATCH_PASSWORD.toString(),
          )
        )
      } else {
        emit(Resource.Loading)
        val result = authRepository.register(request)
        emit(result)
      }
    }
  }.flowOn(Dispatchers.IO)

  private fun checkValidation(request: RegisterRequest): Boolean {
    var isValid = true
    if (request.phone.isEmpty()) {
      request.validation.phoneError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.password.isEmpty()) {
      request.validation.passwordError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.password_confirmation.isEmpty()) {
      request.validation.confirmError.set(Constants.EMPTY)
      isValid = false
    }

    return isValid
  }
}