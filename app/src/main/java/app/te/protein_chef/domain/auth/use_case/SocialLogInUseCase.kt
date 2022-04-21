package app.te.protein_chef.domain.auth.use_case

import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.auth.entity.request.LogInRequest
import app.te.protein_chef.domain.auth.entity.request.SocialLogInRequest
import app.te.protein_chef.domain.auth.repository.AuthRepository
import app.te.protein_chef.domain.utils.*
import app.te.protein_chef.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class SocialLogInUseCase @Inject constructor(
  private val authRepository: AuthRepository,
  private val userLocalUseCase: UserLocalUseCase
) {
  operator fun invoke(
    request: SocialLogInRequest
  ): Flow<Resource<BaseResponse<UserResponse>>> = flow {
    emit(Resource.Loading)
    val result = authRepository.socialLogin(request)
    if (result is Resource.Success) {
      userLocalUseCase.saveUserToken(result.value.data.token)
      userLocalUseCase.invoke(result.value.data)
    }
    emit(result)
  }.flowOn(Dispatchers.IO)

}