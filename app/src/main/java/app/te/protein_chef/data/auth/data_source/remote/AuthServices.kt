package app.te.protein_chef.data.auth.data_source.remote

import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.auth.entity.request.*
import app.te.protein_chef.domain.profile.entity.UpdatePassword
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.*

interface AuthServices {

  @POST("V1/auth/login")
  suspend fun logIn(@Body request: LogInRequest): BaseResponse<UserResponse>

  @POST("V1/auth/social-login")
  suspend fun socialLogIn(@Body request: SocialLogInRequest): BaseResponse<UserResponse>

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