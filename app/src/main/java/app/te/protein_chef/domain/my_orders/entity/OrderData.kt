package app.te.protein_chef.domain.my_orders.entity

import androidx.annotation.Keep

@Keep

data class OrderData(
  val delivered_meals_count: Int = 0,
  val id: Int,
  val meals_count: Int = 0,
  val order_num: String,
  val package_name: String,
  val package_type: String,
  val status: String
)