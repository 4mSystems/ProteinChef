package app.grand.tafwak.data.auth.data_source.remote

import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.auth.entity.request.*
import app.grand.tafwak.domain.profile.entity.UpdatePassword
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.*

interface AuthServices {

  @POST("V1/auth/login")
  suspend fun logIn(@Body request: LogInRequest): BaseResponse<UserResponse>

  @POST("V1/auth/forget-password")
  suspend fun forgetPassword(@Body request: ForgetPasswordRequest): BaseResponse<*>

  @POST("V1/auth/verify")
  suspend fun verifyAccount(@Body request: VerifyAccountRequest): BaseResponse<UserResponse>

  @POST("V1/auth/change_password")
  suspend fun changePassword(@Body request: ChangePasswordRequest): BaseResponse<*>

  @POST("V1/auth/change-password")
  suspend fun updatePassword(@Body request: UpdatePassword): BaseResponse<*>

  @POST("V1/auth/sign-up")
  suspend fun register(@Body request: RegisterRequest): BaseResponse<*>

}