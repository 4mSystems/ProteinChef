package app.grand.tafwak.data.my_locations.data_source

import app.grand.tafwak.domain.my_locations.entity.MyLocationDto
import app.grand.tafwak.domain.my_locations.entity.request.AddLocationRequest
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.*

interface MyLocationsServices {

  @GET("V1/user/locations")
  suspend fun myLocations(): BaseResponse<List<MyLocationDto>>

  @GET("V1/user/location/delete/{locationId}")
  suspend fun removeLocation(@Path("locationId") locationId: Int): BaseResponse<*>

  @POST("V1/user/location/create")
  suspend fun addLocation(@Body addLocationRequest: AddLocationRequest): BaseResponse<MyLocationDto>

}