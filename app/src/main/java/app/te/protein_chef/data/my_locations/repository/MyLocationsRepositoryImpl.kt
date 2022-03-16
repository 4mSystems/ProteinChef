package app.te.protein_chef.data.my_locations.repository

import app.te.protein_chef.data.my_locations.data_source.MyLocationsDataSource
import app.te.protein_chef.domain.my_locations.entity.MyLocationDto
import app.te.protein_chef.domain.my_locations.entity.request.AddLocationRequest
import app.te.protein_chef.domain.my_locations.repository.MyLocationsRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
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