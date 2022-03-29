package app.te.protein_chef.data.make_order.repository

import app.te.protein_chef.data.make_order.data_source.MakeOrderDataSource
import app.te.protein_chef.domain.make_order.entity.MakeOrderRequest
import app.te.protein_chef.domain.make_order.entity.coupon.ApplyCouponRequest
import app.te.protein_chef.domain.make_order.entity.coupon.Coupon
import app.te.protein_chef.domain.make_order.repository.MakeOrderRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import javax.inject.Inject

class MakeOrderRepositoryImpl @Inject constructor(private val remoteDataSource: MakeOrderDataSource) :
  MakeOrderRepository {

  override suspend fun applyCoupon(
    applyCouponRequest: ApplyCouponRequest
  ): Resource<BaseResponse<Coupon>> =
    remoteDataSource.applyCoupon(applyCouponRequest)

  override suspend fun makeOrder(makeOrderRequest: MakeOrderRequest): Resource<BaseResponse<*>> =
    remoteDataSource.makeOrder(makeOrderRequest)


}