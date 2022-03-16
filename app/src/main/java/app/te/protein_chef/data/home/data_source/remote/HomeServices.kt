package app.te.protein_chef.data.home.data_source.remote

import app.te.protein_chef.domain.home.models.HomeMainData
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeServices {
  @GET("V1/user/home")
  suspend fun homeStudent(
    @Query("lat") lat: Double,
    @Query("lng") lng: Double
  ): BaseResponse<HomeMainData>

}