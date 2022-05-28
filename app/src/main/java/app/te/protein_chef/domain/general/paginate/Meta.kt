package app.te.protein_chef.domain.general.paginate

import androidx.annotation.Keep

@Keep
data class Meta(
  val current_page: Int,
  val from: Int,
  val last_page: Int,
  val links: List<Link>,
  val path: String,
  val per_page: Int,
  val to: Int,
  val total: Int
)