package app.te.protein_chef.presentation.make_order.ui_state

import android.content.Context
import app.te.protein_chef.R
import app.te.protein_chef.domain.make_order.entity.MakeOrderRequest
import app.te.protein_chef.domain.make_order.entity.coupon.ApplyCouponRequest
import app.te.protein_chef.domain.make_order.entity.coupon.Coupon
import app.te.protein_chef.presentation.make_order.listener.SubmitOrderEventListener

class SubmitOrderUiState {
  var makeOrderRequest = MakeOrderRequest()
  var applyCouponRequest = ApplyCouponRequest()
  lateinit var submitOrderEventListener: SubmitOrderEventListener

  fun getMealsCost(context: Context): String =
    makeOrderRequest.meals_total.toString().plus(" ").plus(context.getString(R.string.coin))

  fun getMealsAdditionalCost(context: Context): String =
    makeOrderRequest.meals_additional_total.toString().plus(" ")
      .plus(context.getString(R.string.coin))


  fun getDiscountPrice(context: Context): String {
    return if (makeOrderRequest.discount_price.isNullOrEmpty())
      "0.0 ".plus(context.getString(R.string.coin))
    else
      " - ".plus(makeOrderRequest.discount_price).plus(" ").plus(context.getString(R.string.coin))
  }

  fun getTotalCost(context: Context): String {
    return if (makeOrderRequest.discount_price.isNullOrEmpty()) {
      (makeOrderRequest.meals_additional_total + makeOrderRequest.meals_total + makeOrderRequest.deliveryFees).toString()
        .plus(" ")
        .plus(context.getString(R.string.coin))
    } else {
      (makeOrderRequest.total_price + makeOrderRequest.deliveryFees).toString().plus(" ")
        .plus(context.getString(R.string.coin))
    }
  }

  fun getDeliverFees(context: Context): String {
    return makeOrderRequest.deliveryFees.toString().plus(" ")
      .plus(context.getString(R.string.coin))
  }

  fun updateCoupon(couponData: Coupon) {
    makeOrderRequest.coupon_code = applyCouponRequest.coupon_code
    makeOrderRequest.total_price = couponData.new_price
    makeOrderRequest.discount_price = couponData.discount.toString()
  }

  fun applyCoupon(context: Context) {
    applyCouponRequest.price =
      makeOrderRequest.meals_additional_total + makeOrderRequest.meals_total
    if (applyCouponRequest.coupon_code.isEmpty())
      applyCouponRequest.couponError.set(context.getString(R.string.empty_warning))
    else
      submitOrderEventListener.applyCoupon(applyCouponRequest)
  }

  fun makeOrder() {
    submitOrderEventListener.makeOrder()
  }
}