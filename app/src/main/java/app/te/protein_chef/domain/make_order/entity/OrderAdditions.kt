package app.te.protein_chef.domain.make_order.entity

import androidx.annotation.Keep

@Keep
data class OrderAdditions(
  var price: Double = 0.0,
  var meal_type_id: Int = 0,
)
