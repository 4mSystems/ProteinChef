package app.te.protein_chef.domain.make_order.entity.coupon

data class Coupon(
  val done: Boolean = false,
  val type: String = "",
  val old_price: Double = 0.0,
  val discount: Double = 0.0,
  val new_price: Double = 0.0
)
