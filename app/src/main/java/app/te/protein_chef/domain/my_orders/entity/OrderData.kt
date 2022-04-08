package app.te.protein_chef.domain.my_orders.entity

data class OrderData(
  val delivered_meals_count: Int = 0,
  val id: Int,
  val meals_count: Int = 0,
  val order_num: String,
  val package_name: String,
  val package_type: String,
  val status: String
)