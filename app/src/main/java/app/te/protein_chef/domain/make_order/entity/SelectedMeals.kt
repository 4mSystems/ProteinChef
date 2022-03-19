package app.te.protein_chef.domain.make_order.entity

data class SelectedMeals(
  var meal_id: Int = 0,
  var date: String = "",
  var meal_type_id: Int = 0,
)
