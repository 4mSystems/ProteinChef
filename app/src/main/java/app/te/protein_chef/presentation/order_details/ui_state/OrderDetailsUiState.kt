package app.te.protein_chef.presentation.order_details.ui_state

import android.content.Context
import android.view.View
import app.te.protein_chef.R
import app.te.protein_chef.domain.my_orders.entity.order_details.FrozenMeal
import app.te.protein_chef.domain.my_orders.entity.order_details.OrderAdditionPrice
import app.te.protein_chef.domain.my_orders.entity.order_details.OrderMeal
import app.te.protein_chef.domain.my_orders.entity.order_details.OrderStatus
import app.te.protein_chef.domain.packages_categories.entity.category_menu.CategoryMenu
import app.te.protein_chef.presentation.order_details.ui_state.freeze.FreezedItemUiState
import app.te.protein_chef.presentation.packages.ui_state.CategoryMenuUiItem

class OrderDetailsUiState(
  private val discount_price: Double = 0.0,
  private val frozen_meals: List<FrozenMeal> = emptyList(),
  private val location: String? = "",
  private val company_address: String = "",
  private val meal_types: List<CategoryMenu> = emptyList(),
  private val order_addition_prices: List<OrderAdditionPrice> = emptyList(),
  val order_meals: List<OrderMeal> = emptyList(),
  private val package_price: Double = 0.0,
  val remain_frozen_meals: Int = 0,
  private val remaining_days: Int = 0,
  private val shipping_price: Double = 0.0,
  private val total_price: Double = 0.0,
  private val working_hours: String = "",
  private val package_name: String = "",
  private val order_status: String = "",
) {
  var days: Array<String> = emptyArray()
  fun orderRemainDays(context: Context): String =
    remaining_days.toString().plus(" ").plus(
      context.getString(
        R.string.day
      )
    )

  fun orderPackage(): String = package_name

  fun checkIfOrderFromStore(): Int = View.VISIBLE.takeIf { location == null } ?: View.GONE

  fun orderDeliveryLocation(): String =
    location.takeIf { it != null } ?: company_address

  fun orderDeliveryTime(): String = working_hours

  fun getMealsCost(context: Context): String =
    package_price.toString().plus(" ").plus(context.getString(R.string.coin))

  fun getMealsAdditionalCost(context: Context): String =
    order_addition_prices.sumByDouble { it.price }.toString().plus(" ")
      .plus(context.getString(R.string.coin))

  fun orderStatus(context: Context): String {
    return when (order_status) {
      OrderStatus.pending.name -> context.getString(R.string.order_pending)
      OrderStatus.accepted.name -> context.getString(R.string.order_accepted)
      OrderStatus.finished.name -> context.getString(R.string.order_finished)
      OrderStatus.canceled.name -> context.getString(R.string.order_canceled)
      else -> context.getString(R.string.order_in_cancel)
    }


  }

  fun getDiscountPrice(context: Context): String {
    return if (discount_price == 0.0)
      "0.0 ".plus(context.getString(R.string.coin))
    else
      " - ".plus(discount_price).plus(" ").plus(context.getString(R.string.coin))
  }

  fun getTotalCost(context: Context): String {
    return total_price.toString().plus(" ")
      .plus(context.getString(R.string.coin))
  }

  fun getDeliverFees(context: Context): String {
    return shipping_price.toString().plus(" ")
      .plus(context.getString(R.string.coin))
  }

  fun showFreezeButton(): Int =
    if (order_status == OrderStatus.accepted.name && remain_frozen_meals != 0)
      View.VISIBLE
    else
      View.GONE

  fun showCancelButton(): Int =
    if (order_status == OrderStatus.canceled.name)
      View.GONE
    else
      View.VISIBLE

  fun showReorderButton(): Int =
    if (order_status == OrderStatus.canceled.name || order_status == OrderStatus.finished.name)
      View.VISIBLE
    else
      View.GONE

  fun showFrozenMeal(): Int =
    if (frozen_meals.isEmpty())
      View.GONE
    else
      View.VISIBLE

  fun getMealCategories(): MutableList<CategoryMenuUiItem> {
    val items = mutableListOf<CategoryMenuUiItem>()
    meal_types.map { menu ->
      items.add(
        CategoryMenuUiItem(
          id = menu.id,
          meal_type_id = menu.meal_type_id,
          title = menu.title,
          image = menu.image,
          price = menu.price
        )
      )
    }
    return items
  }

  fun getOrderMealsItemUiState(mealTypes: List<OrderMeal>): MutableList<OrderMealsItemUiStat> {
    val orderMealsList: MutableList<OrderMealsItemUiStat> = mutableListOf()
    mealTypes.forEach {
      orderMealsList.add(
        OrderMealsItemUiStat(
          it.id,
          it.title,
          it.date,
          it.image
        )
      )
    }
    return orderMealsList
  }

  fun getOrderFreezedMealsItemUiState(): MutableList<FreezedItemUiState> {
    val orderMealsList: MutableList<FreezedItemUiState> = mutableListOf()
    frozen_meals.forEach {
      orderMealsList.add(
        FreezedItemUiState(
          it.old_date,
          it.date
        )
      )
    }
    return orderMealsList
  }

}