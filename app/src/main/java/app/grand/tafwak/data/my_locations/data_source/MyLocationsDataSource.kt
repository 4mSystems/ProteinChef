package app.grand.tafwak.data.my_locations.data_source

import app.grand.tafwak.data.remote.BaseRemoteDataSource
import app.grand.tafwak.domain.my_locations.entity.request.AddLocationRequest
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