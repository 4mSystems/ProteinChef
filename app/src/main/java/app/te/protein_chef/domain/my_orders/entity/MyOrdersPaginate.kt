package app.te.protein_chef.domain.my_orders.entity

import androidx.annotation.Keep
import app.te.protein_chef.domain.general.paginate.Links
import app.te.protein_chef.domain.general.paginate.Meta
import com.google.gson.annotations.SerializedName

@Keep
data class MyOrdersPaginate(
  @SerializedName("data")
  val myOrdersData: List<OrderData>,

  val meta: Meta? = null,
  @SerializedName("links")
  val links: Links? = null
)