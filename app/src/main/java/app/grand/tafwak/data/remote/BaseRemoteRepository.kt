package app.grand.tafwak.data.remote

interface BaseRemoteRepository {
  suspend fun clearPreferences()
}