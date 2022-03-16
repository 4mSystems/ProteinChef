package app.te.protein_chef.domain.my_locations.repository

import app.te.protein_chef.domain.my_locations.entity.MyLocationDto
import app.te.protein_chef.domain.my_locations.entity.request.AddLocationRequest
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface MyLocationsRepository {
  suspend fun getMyLocations(): Resource<BaseResponse<List<MyLocationDto>>>
  suspend fun removeLocation(locationId: Int): Resource<BaseResponse<*>>
  suspend fun addLocation(addLocationRequest: AddLocationRequest): Resource<BaseResponse<MyLocationDto>>
}