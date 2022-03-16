package app.te.protein_chef.data.auth.data_source.remote

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import app.te.protein_chef.domain.auth.entity.request.*
import app.te.protein_chef.domain.profile.entity.UpdatePassword
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val apiService: AuthServices) :
  BaseRemoteDataSource() {

  suspend fun logIn(request: LogInRequest) = safeApiCall {
    apiService.logIn(request)
  }

  suspend fun forgetPassword(request: ForgetPasswordRequest) = safeApiCall {
    apiService.forgetPassword(request)
  }

  suspend fun verifyAccount(request: VerifyAccountRequest) = safeApiCall {
    apiService.verifyAccount(request)
  }

  suspend fun changePassword(request: ChangePasswordRequest) = safeApiCall {
    apiService.changePassword(request)
  }

  suspend fun updatePassword(request: UpdatePassword) = safeApiCall {
    apiService.updatePassword(request)
  }

  suspend fun register(request: RegisterRequest) = safeApiCall {
    apiService.register(request)
  }


}