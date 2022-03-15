package app.grand.tafwak.domain.auth.use_case

import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.auth.entity.request.VerifyAccountRequest
import app.grand.tafwak.domain.auth.repository.AuthRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class VerifyAccountUseCase @Inject constructor(
  private val authRepository: AuthRepository,
  private val userLocalUseCase: UserLocalUseCase
) {

  operator fun invoke(request: VerifyAccountRequest): Flow<Resource<BaseResponse<UserResponse>>> =
    flow {
      if (request.code.isNotEmpty()) {
        emit(Resource.Loading)
        val result = authRepository.verifyAccount(request)
        if (result is Resource.Success) {
          if (request.type == Constants.Verify)
            userLocalUseCase.invoke(result.value.data)
          userLocalUseCase.saveUserToken(result.value.data.token)
        }
        emit(result)
      }
    }.flowOn(Dispatchers.IO)
}