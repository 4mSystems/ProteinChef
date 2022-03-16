package app.te.protein_chef.data.profile.data_source

import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.profile.entity.UpdateProfileRequest
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.*

interface ProfileServices {

  @POST("V1/auth/update-profile")
  suspend fun updateProfile(@Body request: UpdateProfileRequest): BaseResponse<UserResponse>

}