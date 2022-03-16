package app.te.protein_chef.data.remote

interface BaseRemoteRepository {
  suspend fun clearPreferences()
}