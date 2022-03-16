package app.te.protein_chef.data.settings.data_source.remote

import app.te.protein_chef.domain.settings.models.ContactUsRequest
import app.te.protein_chef.domain.settings.models.SettingsData
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.*

interface SettingsServices {
  @GET("V1/app/pages/{type}")
  suspend fun settings(
    @Path("type") type: String
  ): BaseResponse<SettingsData>

  @GET("v1/settings")
  suspend fun social(
    @Query("type") type: String,
    @Query("app_type") app_type: String
  ): BaseResponse<List<SettingsData>>

  @POST("v1/contact_app")
  suspend fun contactApp(@Body contactUsRequest: ContactUsRequest): BaseResponse<*>


}