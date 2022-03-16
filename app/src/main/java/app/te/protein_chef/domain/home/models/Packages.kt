package app.te.protein_chef.domain.home.models

import androidx.annotation.Keep

@Keep
data class Packages(
  val title: String = "",
  val image: String = "",
  val id: Int = 0
)