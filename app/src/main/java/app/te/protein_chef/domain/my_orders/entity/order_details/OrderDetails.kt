package app.te.protein_chef.domain.my_orders.entity.order_details

import androidx.annotation.Keep
import app.te.protein_chef.domain.packages_categories.entity.category_menu.CategoryMenu
@Keep

data class OrderDetails(
  val discount_price: Double,
  val frozen_meals: List<FrozenMeal>,
  val location: String? = "",
  val meal_types: List<CategoryMenu>,
  val order_addition_prices: List<OrderAdditionPrice>,
  val order_meals: List<OrderMeal>,
  val package_price: Double,
  val remain_frozen_meals: Int,
  val remaining_days: Int,
  val shipping_price: Double,
  val total_price: Double,
  val working_hours: String,
  val package_name: String,
  val order_status: String,
  val company_address: CompanyAddress
)