package app.te.protein_chef.domain.general.paginate

data class Links(
  val next: String? = null,
  val last: String = "",
  val prev: String = "",
  val first: String = "",
)