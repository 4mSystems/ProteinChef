package app.grand.tafwak.data.profile.data_source

import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.profile.entity.UpdateProfileRequest
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.*

interface ProfileServices {

  @POST("V1/auth/update-profile")
  suspend fun updateProfile(@Body request: UpdateProfileRequest): BaseResponse<UserResponse>

}