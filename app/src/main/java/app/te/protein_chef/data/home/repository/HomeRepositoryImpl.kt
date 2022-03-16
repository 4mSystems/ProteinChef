package app.te.protein_chef.data.home.repository

import app.te.protein_chef.data.home.data_source.remote.HomeRemoteDataSource
import app.te.protein_chef.domain.home.models.HomeMainData
import app.te.protein_chef.domain.home.repository.HomeRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource) :
  HomeRepository {
  override suspend fun getHome(lat: Double, lng: Double): Resource<BaseResponse<HomeMainData>> =
    remoteDataSource.homeStudent(lat, lng)
}