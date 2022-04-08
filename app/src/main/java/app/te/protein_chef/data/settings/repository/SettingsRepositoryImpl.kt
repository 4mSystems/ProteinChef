package app.te.protein_chef.data.settings.repository

import app.te.protein_chef.data.settings.data_source.remote.SettingsRemoteDataSource
import app.te.protein_chef.domain.settings.models.ContactUsRequest
import app.te.protein_chef.domain.settings.models.SettingsData
import app.te.protein_chef.domain.settings.models.order_settings.OrderSettings
import app.te.protein_chef.domain.settings.repository.SettingsRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val remoteDataSource: SettingsRemoteDataSource) :
  SettingsRepository {
  override suspend fun settings(
    type: String
  ): Resource<BaseResponse<SettingsData>> = remoteDataSource.settings(type)

  override suspend fun social(
    type: String,
    app_type: String
  ): Resource<BaseResponse<List<SettingsData>>> = remoteDataSource.social(type, app_type)

  override suspend fun contactApp(contactUsRequest: ContactUsRequest): Resource<BaseResponse<*>> =
    remoteDataSource.contactApp(contactUsRequest)

  override suspend fun orderCustomSettings(): Resource<BaseResponse<OrderSettings>> =
    remoteDataSource.orderCustomSettings()
}