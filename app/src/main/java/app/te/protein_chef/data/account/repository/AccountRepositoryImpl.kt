package app.te.protein_chef.data.account.repository

import app.te.protein_chef.data.account.data_source.remote.AccountRemoteDataSource
import app.te.protein_chef.data.local.preferences.AppPreferences
import app.te.protein_chef.domain.account.entity.request.SendFirebaseTokenRequest
import app.te.protein_chef.domain.account.repository.AccountRepository
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.presentation.my_locations.ui_state.MyLocationsDataUiState
import com.structure.base_mvvm.DefaultLocation
import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
  private val remoteDataSource: AccountRemoteDataSource,
  private val appPreferences: AppPreferences
) : AccountRepository {

  override
  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest) =
    remoteDataSource.sendFirebaseToken(request)

  override
  suspend fun logOut() = remoteDataSource.logOut()
  override suspend fun isLoggedIn(isLoggedIn: Boolean) {
    appPreferences.isLoggedIn(isLoggedIn)
  }

  override suspend fun getIsLoggedIn(): Flow<Boolean> {
    return appPreferences.getIsLoggedIn()
  }


  override suspend fun saveFirebaseTokenToLocal(firebaseToken: String) {
    appPreferences.saveFireBaseToken(firebaseToken)
  }

  override suspend fun getFirebaseTokenToLocal(): Flow<String> {
    return appPreferences.getFireBaseToken()
  }

  override suspend fun setFirstTime(isFirstTime: Boolean) {
    appPreferences.isFirstTime(isFirstTime)
  }

  override suspend fun isFirstTime(): Flow<Boolean> {
    return appPreferences.getIsFirstTime()
  }

  override suspend fun saveUserToLocal(user: UserResponse) {
    appPreferences.saveUser(user)
  }

  override suspend fun getUserToLocal(): Flow<User> {
    return appPreferences.getUser()
  }

  override suspend fun saveUserToken(userToken: String) {
    appPreferences.userToken(userToken)
  }

  override suspend fun getUserToken(): Flow<String> {
    return appPreferences.getUserToken()
  }

  override suspend fun saveDefaultLocation(locationsUiState: MyLocationsDataUiState) {
    appPreferences.saveDefaultLocation(locationsUiState)
  }

  override suspend fun getDefaultLocation(): Flow<DefaultLocation> =
    appPreferences.getDefaultLocation()

  override suspend fun setLang(lang: String) {
    appPreferences.setLang(lang)
  }

  override suspend fun getLang(): Flow<String> {
    return appPreferences.getLang()
  }

  override
  suspend fun clearPreferences() = appPreferences.clearPreferences()
}