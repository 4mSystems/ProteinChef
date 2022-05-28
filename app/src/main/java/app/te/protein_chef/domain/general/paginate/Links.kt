package app.te.protein_chef.domain.general.paginate

import androidx.annotation.Keep

@Keep
data class Links(
  val first: String,
  val last: String,
  val next: String? = null,
  val prev: Any
)