package app.grand.tafwak.data.my_locations.repository

import app.grand.tafwak.data.my_locations.data_source.MyLocationsDataSource
import app.grand.tafwak.domain.my_locations.entity.MyLocationDto
import app.grand.tafwak.domain.my_locations.entity.request.AddLocationRequest
import app.grand.tafwak.domain.my_locations.repository.MyLocationsRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import javax.inject.Inject

class MyLocationsRepositoryImpl @Inject constructor(
  private val remoteDataSource: MyLocationsDataSource
) : MyLocationsRepository {

  override suspend fun getMyLocations(): Resource<BaseResponse<List<MyLocationDto>>> =
    remoteDataSource.myLocations()

  override suspend fun removeLocation(locationId: Int): Resource<BaseResponse<*>> =
    remoteDataSource.removeLocation(locationId)

  override suspend fun addLocation(addLocationRequest: AddLocationRequest): Resource<BaseResponse<MyLocationDto>> =remoteDataSource.addLocation(addLocationRequest)

}