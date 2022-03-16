package app.te.protein_chef.domain.profile.repository

import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.profile.entity.UpdateProfileRequest
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface ProfileRepository {
  suspend fun updateProfile(request: UpdateProfileRequest): Resource<BaseResponse<UserResponse>>
}