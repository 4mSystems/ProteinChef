package app.te.protein_chef.domain.make_order.entity

data class OrderAdditions(
  var meal_id: Int = 0,
  var price: Double = 0.0,
  var meal_type_id: Int = 0,
)
