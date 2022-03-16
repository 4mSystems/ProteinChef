package app.te.protein_chef.data.profile.data_source

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import app.te.protein_chef.domain.profile.entity.UpdateProfileRequest
import javax.inject.Inject

class ProfileDataSource @Inject constructor(private val apiService: ProfileServices) :
  BaseRemoteDataSource() {

  suspend fun updateProfile(request: UpdateProfileRequest) = safeApiCall {
    apiService.updateProfile(request)
  }

}