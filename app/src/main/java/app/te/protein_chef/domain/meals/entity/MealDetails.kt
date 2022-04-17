package app.te.protein_chef.domain.meals.entity

import androidx.annotation.Keep

@Keep

data class MealDetails(
  var id: Int = 0,
  var title: String = "",
  var body: String = "",
  var image: String = "",
)
