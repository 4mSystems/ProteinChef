package app.te.protein_chef.presentation.make_order.listener

import app.te.protein_chef.domain.make_order.entity.coupon.ApplyCouponRequest

interface SubmitOrderEventListener {
  fun applyCoupon(applyCouponRequest: ApplyCouponRequest)
  fun makeOrder()
}