package app.te.protein_chef.domain.account.repository

import app.te.protein_chef.domain.account.entity.request.SendFirebaseTokenRequest
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.my_locations.ui_state.MyLocationsDataUiState
import com.structure.base_mvvm.DefaultLocation
import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest): Resource<BaseResponse<*>>

  suspend fun logOut(): Resource<BaseResponse<*>>

  suspend fun isLoggedIn(isLoggedIn: Boolean)

  suspend fun getIsLoggedIn(): Flow<Boolean>

  suspend fun saveFirebaseTokenToLocal(firebaseToken: String)

  suspend fun getFirebaseTokenToLocal(): Flow<String>

  suspend fun setFirstTime(isFirstTime: Boolean)

  suspend fun isFirstTime(): Flow<Boolean>

  suspend fun saveUserToLocal(user: UserResponse)

  suspend fun getUserToLocal(): Flow<User>

  suspend fun saveUserToken(userToken: String)

  suspend fun getUserToken(): Flow<String>

  suspend fun saveDefaultLocation(locationsUiState: MyLocationsDataUiState)

  suspend fun getDefaultLocation(): Flow<DefaultLocation>

  suspend fun setLang(lang: String)

  suspend fun getLang(): Flow<String>

  suspend fun saveShippingValue(value: String)

  suspend fun getShippingValue(): Flow<String>

  suspend fun saveWorkingHours(value: String)

  suspend fun getWorkingHours(): Flow<String>

  suspend fun clearPreferences()

}