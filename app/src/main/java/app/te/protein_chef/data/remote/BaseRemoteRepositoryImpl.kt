package app.te.protein_chef.data.remote

import app.te.protein_chef.data.local.preferences.AppPreferences
import javax.inject.Inject

class BaseRemoteRepositoryImpl @Inject constructor(
  private val appPreferences: AppPreferences
) : BaseRemoteRepository {
  override
  suspend fun clearPreferences() = appPreferences.clearPreferences()
}