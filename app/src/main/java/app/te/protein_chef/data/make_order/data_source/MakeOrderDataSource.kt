package app.te.protein_chef.data.make_order.data_source

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import app.te.protein_chef.domain.make_order.entity.MakeOrderRequest
import app.te.protein_chef.domain.make_order.entity.coupon.ApplyCouponRequest
import javax.inject.Inject

class MakeOrderDataSource @Inject constructor(private val apiService: MakeOrderServices) :
  BaseRemoteDataSource() {

  suspend fun applyCoupon(
    applyCouponRequest: ApplyCouponRequest
  ) = safeApiCall {
    apiService.applyCoupon(applyCouponRequest)
  }

  suspend fun makeOrder(
    makeOrderRequest: MakeOrderRequest
  ) = safeApiCall {
    apiService.makeOrder(makeOrderRequest)
  }

}