package app.te.protein_chef.domain.meals.entity

import androidx.annotation.Keep

@Keep
data class MainMealType(
  val id: Int = 0,
  val meal_id: Int = 0,
  val image: String = "",
  var selected: Int = 0,
  val title: String = "",
  val day: String = "",
  val date: String = "",
  val week: String = "",
)