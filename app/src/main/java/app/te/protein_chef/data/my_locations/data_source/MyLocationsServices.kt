package app.te.protein_chef.data.my_locations.data_source

import app.te.protein_chef.domain.my_locations.entity.MyLocationDto
import app.te.protein_chef.domain.my_locations.entity.request.AddLocationRequest
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.*

interface MyLocationsServices {

  @GET("V1/user/locations")
  suspend fun myLocations(): BaseResponse<List<MyLocationDto>>

  @GET("V1/user/location/delete/{locationId}")
  suspend fun removeLocation(@Path("locationId") locationId: Int): BaseResponse<*>

  @POST("V1/user/location/create")
  suspend fun addLocation(@Body addLocationRequest: AddLocationRequest): BaseResponse<MyLocationDto>

}