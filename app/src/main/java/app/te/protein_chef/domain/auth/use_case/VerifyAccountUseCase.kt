package app.te.protein_chef.domain.auth.use_case

import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.auth.entity.request.VerifyAccountRequest
import app.te.protein_chef.domain.auth.repository.AuthRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.utils.Constants
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