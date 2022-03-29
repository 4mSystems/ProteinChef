package app.te.protein_chef.data.make_order.data_source

import app.te.protein_chef.domain.make_order.entity.MakeOrderRequest
import app.te.protein_chef.domain.make_order.entity.coupon.ApplyCouponRequest
import app.te.protein_chef.domain.make_order.entity.coupon.Coupon
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MakeOrderServices {
  @POST("V1/user/apply/coupon")
  suspend fun applyCoupon(
    @Body applyCouponRequest: ApplyCouponRequest
  ): BaseResponse<Coupon>

  @POST("V1/user/make_order")
  suspend fun makeOrder(
    @Body makeOrderRequest: MakeOrderRequest
  ): BaseResponse<*>

}