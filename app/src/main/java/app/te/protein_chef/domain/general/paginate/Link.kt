package app.te.protein_chef.domain.general.paginate

import androidx.annotation.Keep

@Keep
data class Link(
  val active: Boolean,
  val label: String,
  val url: Any
)