package app.te.protein_chef.domain.my_orders.entity

import androidx.annotation.Keep
import app.te.protein_chef.domain.general.paginate.Paginate
import com.google.gson.annotations.SerializedName

@Keep
data class MyOrdersPaginate(
  @SerializedName("data")
  val myOrdersData: List<OrderData>
) : Paginate()