package app.grand.tafwak.data.profile.repository

import app.grand.tafwak.data.profile.data_source.ProfileDataSource
import app.grand.tafwak.domain.profile.entity.UpdateProfileRequest
import app.grand.tafwak.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
  private val remoteDataSource: ProfileDataSource
) : ProfileRepository {

  override
  suspend fun updateProfile(request: UpdateProfileRequest) = remoteDataSource.updateProfile(request)

}