package app.grand.tafwak.domain.profile.repository

import app.grand.tafwak.domain.auth.entity.model.UserResponse
import app.grand.tafwak.domain.profile.entity.UpdateProfileRequest
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource

interface ProfileRepository {
  suspend fun updateProfile(request: UpdateProfileRequest): Resource<BaseResponse<UserResponse>>
}