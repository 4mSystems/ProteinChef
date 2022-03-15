package app.grand.tafwak.data.remote

import app.grand.tafwak.data.local.preferences.AppPreferences
import javax.inject.Inject

class BaseRemoteRepositoryImpl @Inject constructor(
  private val appPreferences: AppPreferences
) : BaseRemoteRepository {
  override
  suspend fun clearPreferences() = appPreferences.clearPreferences()
}