package app.te.protein_chef.data.home.data_source.remote

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeServices) :
  BaseRemoteDataSource() {

  suspend fun homeStudent(lat: Double, lng: Double) = safeApiCall {
    apiService.homeStudent(lat, lng)
  }
}