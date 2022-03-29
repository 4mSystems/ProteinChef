package app.te.protein_chef.domain.make_order.entity.coupon

import androidx.databinding.ObservableField

class ApplyCouponRequest {
  var coupon_code: String = ""
    set(value) {
      couponError.set(null)
      field = value
    }
  var price: Double = 0.0

  @Transient
  var couponError: ObservableField<String> = ObservableField<String>()

}
