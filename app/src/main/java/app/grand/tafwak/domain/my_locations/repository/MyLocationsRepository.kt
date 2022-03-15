package app.grand.tafwak.domain.my_locations.repository

import app.grand.tafwak.domain.my_locations.entity.MyLocationDto
import app.grand.tafwak.domain.my_locations.entity.request.AddLocationRequest
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource

interface MyLocationsRepository {
  suspend fun getMyLocations(): Resource<BaseResponse<List<MyLocationDto>>>
  suspend fun removeLocation(locationId: Int): Resource<BaseResponse<*>>
  suspend fun addLocation(addLocationRequest: AddLocationRequest): Resource<BaseResponse<MyLocationDto>>
}