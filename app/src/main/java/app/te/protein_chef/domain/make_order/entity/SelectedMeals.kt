package app.te.protein_chef.domain.make_order.entity

import androidx.annotation.Keep

@Keep
data class SelectedMeals(
  var meal_id: Int = 0,
  var date: String = "",
  var meal_type_id: Int = 0,
)
