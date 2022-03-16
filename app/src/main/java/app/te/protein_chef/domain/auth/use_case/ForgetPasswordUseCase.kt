package app.te.protein_chef.domain.auth.use_case

import app.te.protein_chef.domain.auth.entity.request.ForgetPasswordRequest
import app.te.protein_chef.domain.auth.repository.AuthRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ForgetPasswordUseCase @Inject constructor(
  private val authRepository: AuthRepository
) {
  operator fun invoke(request: ForgetPasswordRequest): Flow<Resource<BaseResponse<*>>> = flow {
    if (request.phone.isNotEmpty()) {
      emit(Resource.Loading)
      val result = authRepository.forgetPassword(request)
      emit(result)
    }
  }.flowOn(Dispatchers.IO)
}