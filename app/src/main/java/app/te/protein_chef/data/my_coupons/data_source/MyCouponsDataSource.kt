package app.te.protein_chef.data.my_coupons.data_source

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class MyCouponsDataSource @Inject constructor(private val apiService: MyCouponsServices) :
  BaseRemoteDataSource() {

  suspend fun myCoupons() = safeApiCall {
    apiService.myCoupons()
  }

}