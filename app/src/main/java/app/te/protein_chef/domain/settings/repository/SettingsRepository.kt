package app.te.protein_chef.domain.settings.repository

import app.te.protein_chef.domain.settings.models.ContactUsRequest
import app.te.protein_chef.domain.settings.models.SettingsData
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface SettingsRepository {
  suspend fun settings(type: String): Resource<BaseResponse<SettingsData>>
  suspend fun social(type: String, app_type: String): Resource<BaseResponse<List<SettingsData>>>
  suspend fun contactApp(contactUsRequest: ContactUsRequest): Resource<BaseResponse<*>>

}