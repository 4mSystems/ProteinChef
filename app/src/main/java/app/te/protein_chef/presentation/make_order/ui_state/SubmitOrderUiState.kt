package app.te.protein_chef.presentation.make_order.ui_state

import android.content.Context
import app.te.protein_chef.R
import app.te.protein_chef.domain.make_order.entity.MakeOrderRequest

class SubmitOrderUiState {
  var makeOrderRequest = MakeOrderRequest()

  fun getMealsCost(context: Context): String =
    makeOrderRequest.meals_total.toString().plus(" ").plus(context.getString(R.string.coin))

  fun getMealsAdditionalCost(context: Context): String =
    makeOrderRequest.meals_additional_total.toString().plus(" ")
      .plus(context.getString(R.string.coin))

  fun getDiscountPrice(context: Context): String {
    return if (makeOrderRequest.discount_price != null)
      makeOrderRequest.discount_price.plus(context.getString(R.string.coin))
    else
      "0.0 ".plus(context.getString(R.string.coin))
  }
}