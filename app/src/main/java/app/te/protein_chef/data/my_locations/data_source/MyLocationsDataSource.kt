package app.te.protein_chef.data.my_locations.data_source

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import app.te.protein_chef.domain.my_locations.entity.request.AddLocationRequest
import javax.inject.Inject

class MyLocationsDataSource @Inject constructor(private val apiService: MyLocationsServices) :
  BaseRemoteDataSource() {

  suspend fun myLocations() = safeApiCall {
    apiService.myLocations()
  }

  suspend fun removeLocation(locationId: Int) = safeApiCall {
    apiService.removeLocation(locationId)
  }

  suspend fun addLocation(addLocationRequest: AddLocationRequest) = safeApiCall {
    apiService.addLocation(addLocationRequest)
  }

}