package app.te.protein_chef.data.settings.data_source.remote

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import app.te.protein_chef.domain.settings.models.ContactUsRequest
import javax.inject.Inject

class SettingsRemoteDataSource @Inject constructor(private val apiService: SettingsServices) :
  BaseRemoteDataSource() {
  suspend fun settings(type: String) = safeApiCall {
    apiService.settings(type)
  }

  suspend fun social(type: String, app_type: String) = safeApiCall {
    apiService.social(type, app_type)
  }

  suspend fun contactApp(contactUsRequest: ContactUsRequest) = safeApiCall {
    apiService.contactApp(contactUsRequest)
  }

  suspend fun orderCustomSettings() = safeApiCall {
    apiService.orderSettings()
  }

}