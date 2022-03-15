package app.grand.tafwak.data.profile.data_source

import app.grand.tafwak.data.remote.BaseRemoteDataSource
import app.grand.tafwak.domain.profile.entity.UpdateProfileRequest
import javax.inject.Inject

class ProfileDataSource @Inject constructor(private val apiService: ProfileServices) :
  BaseRemoteDataSource() {

  suspend fun updateProfile(request: UpdateProfileRequest) = safeApiCall {
    apiService.updateProfile(request)
  }

}