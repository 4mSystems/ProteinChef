package app.te.protein_chef.domain.make_order.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class MakeOrderRequest(
  var selected_meal: @RawValue MutableList<SelectedMeals> = mutableListOf(),
  var order_additions: @RawValue MutableList<OrderAdditions> = mutableListOf(),
  var selected_date: String = "",
  var location_id: Int? = null,
  var package_type_id: Int = 0,
  var coupon_code: String? = null,
  var discount_price: String? = null,
  @Transient
  var total_price: Double = 0.0,
  @Transient
  var deliveryFees: Double = 0.0,
  var meals_total: Double = 0.0,
  var meals_additional_total: Double = 0.0,
) : Parcelable
