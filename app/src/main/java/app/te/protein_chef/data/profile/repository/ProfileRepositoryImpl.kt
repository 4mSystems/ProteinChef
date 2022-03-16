package app.te.protein_chef.data.profile.repository

import app.te.protein_chef.data.profile.data_source.ProfileDataSource
import app.te.protein_chef.domain.profile.entity.UpdateProfileRequest
import app.te.protein_chef.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
  private val remoteDataSource: ProfileDataSource
) : ProfileRepository {

  override
  suspend fun updateProfile(request: UpdateProfileRequest) = remoteDataSource.updateProfile(request)

}