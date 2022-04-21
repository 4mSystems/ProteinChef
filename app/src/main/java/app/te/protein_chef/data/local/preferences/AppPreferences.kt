package app.te.protein_chef.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.auth.entity.model.UserSerializer
import app.te.protein_chef.domain.my_locations.entity.DefaultLocationSerializer
import app.te.protein_chef.presentation.my_locations.ui_state.MyLocationsDataUiState
import com.structure.base_mvvm.DefaultLocation
import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferences @Inject constructor(private val context: Context) {

  private val STORE_NAME = "app_data_store"
  private val STORE_NAME_FIRST_TIME = "app_data_store_first_time"
  private val USER_DATA_STORE_FILE_NAME = "user_store.pb"
  private val DEFAULTLOCATIONFILE = "location_store.pb"

  private val FIREBASE_TOKEN = stringPreferencesKey("FIREBASE_TOKEN")
  private val USER_TOKEN = stringPreferencesKey("USER_TOKEN")
  private val COUNTRY_ID = stringPreferencesKey("COUNTRY_ID")
  private val FIRST_TIME = booleanPreferencesKey("FIRST_TIME")
  private val IS_LOGGED_IN = booleanPreferencesKey("isLoggedIn")
  private val LANG = stringPreferencesKey("LANG")
  private val SHIPPING_VALUE = stringPreferencesKey("SHIPPING_VALUE")
  private val WORKING_HOURS = stringPreferencesKey("WORKING_HOURS")


  private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)
  private val Context.dataStoreFirstTime: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME_FIRST_TIME)
  private val Context.userDataStore: DataStore<User> by dataStore(
    fileName = USER_DATA_STORE_FILE_NAME,
    serializer = UserSerializer
  )
  private val Context.defaultLocationDataStore: DataStore<DefaultLocation> by dataStore(
    fileName = DEFAULTLOCATIONFILE,
    serializer = DefaultLocationSerializer
  )

  suspend fun saveFireBaseToken(token: String) {
    context.dataStore.edit {
      it[FIREBASE_TOKEN] = token
    }
  }

  fun getFireBaseToken() = context.dataStore.data.map {
    it[FIREBASE_TOKEN] ?: ""
  }

  suspend fun isFirstTime(isFirstTime: Boolean) {
    context.dataStoreFirstTime.edit {
      it[FIRST_TIME] = isFirstTime
    }
  }

  fun getIsFirstTime() = context.dataStoreFirstTime.data.map {
    it[FIRST_TIME] ?: true
  }

  suspend fun isLoggedIn(isLoggedIn: Boolean) {
    context.dataStore.edit {
      it[IS_LOGGED_IN] = isLoggedIn
    }
  }

  fun getIsLoggedIn() = context.dataStore.data.map {
    it[IS_LOGGED_IN] ?: false
  }

  suspend fun userToken(userToken: String) {
    context.dataStore.edit {
      it[USER_TOKEN] = userToken
    }
  }

  fun getUserToken() = context.dataStore.data.map {
    it[USER_TOKEN] ?: ""
  }

  suspend fun saveDefaultLocation(locationsUiState: MyLocationsDataUiState) {
    context.defaultLocationDataStore.updateData { location ->
      location.toBuilder()
        .setId(locationsUiState.getId())
        .setTitle(locationsUiState.getTitle())
        .setBody(locationsUiState.getAddress())
        .build()
    }
  }

  fun getDefaultLocation(): Flow<DefaultLocation> = context.defaultLocationDataStore.data

  suspend fun setLang(lang: String) {
    context.dataStore.edit {
      it[LANG] = lang
    }
  }

  fun getLang() = context.dataStore.data.map {
    it[LANG] ?: "ar"
  }

  suspend fun saveUser(user: UserResponse) {
    context.userDataStore.updateData { store ->
      store.toBuilder()
        .setId(user.id)
        .setEmail(user.email ?: "")
        .setName(user.name ?: "")
        .setImage(user.image ?: "")
        .setIsCompleted(user.is_completed)
        .setPhone(user.phone)
        .setGender(user.gender ?: "")
        .setToken(user.token)
        .setAge(user.age)
        .setWeight(user.weight)
        .setHeight(user.height)
        .setSocialToken(user.social_id)
        .build()

    }
  }

  fun getUser(): Flow<User> = context.userDataStore.data
  suspend fun saveShippingValue(shippingValue: String) {
    context.dataStore.edit {
      it[SHIPPING_VALUE] = shippingValue
    }
  }

  fun getShippingValue() = context.dataStore.data.map {
    it[SHIPPING_VALUE] ?: ""
  }

  suspend fun workingHours(shippingValue: String) {
    context.dataStore.edit {
      it[WORKING_HOURS] = shippingValue
    }
  }

  fun getWorkingHours() = context.dataStore.data.map {
    it[WORKING_HOURS] ?: ""
  }


  suspend fun clearPreferences() {
    context.dataStore.edit {
      it.clear()
    }
    context.userDataStore.updateData {
      it.toBuilder().clear().build()
    }
  }
}