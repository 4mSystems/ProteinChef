package app.te.protein_chef.presentation.my_orders.ui_state

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemMyOrderBinding
import app.te.protein_chef.domain.my_orders.entity.OrderData
import app.te.protein_chef.presentation.my_orders.listeners.MyOrdersListener
import kotlin.math.roundToInt

class MyOrderDataUiState(private val orderData: OrderData) : MyOrdersUiState {
  override fun getLayoutRes(): Int = R.layout.item_my_order
  override fun bind(
    item: View?,
    position: Int,
    context: Context,
    myOrdersListener: MyOrdersListener
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemMyOrderBinding>(item)
    binding?.uiState = this
    binding?.eventListener = myOrdersListener
  }


  override fun getId(): Int = orderData.id
  fun getOrderNumber(): String = "#".plus(orderData.order_num)
  fun getOrderPackageType(): String = orderData.package_type
  fun getOrderOrderCount(): String =
    orderData.meals_count.toString().plus(" / ").plus(orderData.delivered_meals_count)

  fun orderProgress(): Int {
    return if (orderData.meals_count == 0)
      0
    else {
      ((orderData.delivered_meals_count.toDouble() / orderData.meals_count.toDouble()) * 100).roundToInt()
    }
  }
}