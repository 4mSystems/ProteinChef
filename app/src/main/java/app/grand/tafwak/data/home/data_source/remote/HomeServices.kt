package app.grand.tafwak.data.home.data_source.remote

import app.grand.tafwak.domain.home.models.HomeMainData
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeServices {
  @GET("V1/user/home")
  suspend fun homeStudent(
    @Query("lat") lat: Double,
    @Query("lng") lng: Double
  ): BaseResponse<HomeMainData>

}