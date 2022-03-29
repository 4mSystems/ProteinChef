package app.te.protein_chef.domain.make_order.repository

import app.te.protein_chef.domain.make_order.entity.MakeOrderRequest
import app.te.protein_chef.domain.make_order.entity.coupon.ApplyCouponRequest
import app.te.protein_chef.domain.make_order.entity.coupon.Coupon
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface MakeOrderRepository {

  suspend fun applyCoupon(
    applyCouponRequest: ApplyCouponRequest
  ): Resource<BaseResponse<Coupon>>

  suspend fun makeOrder(
    makeOrderRequest: MakeOrderRequest
  ): Resource<BaseResponse<*>>

}