package app.te.protein_chef.data.auth.repository

import app.te.protein_chef.data.auth.data_source.remote.AuthRemoteDataSource
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.auth.entity.request.*
import app.te.protein_chef.domain.auth.repository.AuthRepository
import app.te.protein_chef.domain.profile.entity.UpdatePassword
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

  override
  suspend fun logIn(request: LogInRequest) = remoteDataSource.logIn(request)

  override suspend fun socialLogin(request: SocialLogInRequest): Resource<BaseResponse<UserResponse>> =
    remoteDataSource.socialLogIn(request)

  override suspend fun changePassword(request: ChangePasswordRequest): Resource<BaseResponse<*>> =
    remoteDataSource.changePassword(request)

  override suspend fun updatePassword(request: UpdatePassword): Resource<BaseResponse<*>> =
    remoteDataSource.updatePassword(request)

  override suspend fun forgetPassword(request: ForgetPasswordRequest) =
    remoteDataSource.forgetPassword(request)

  override suspend fun register(request: RegisterRequest): Resource<BaseResponse<*>> =
    remoteDataSource.register(request)

  override suspend fun verifyAccount(request: VerifyAccountRequest): Resource<BaseResponse<UserResponse>> =
    remoteDataSource.verifyAccount(request)

}