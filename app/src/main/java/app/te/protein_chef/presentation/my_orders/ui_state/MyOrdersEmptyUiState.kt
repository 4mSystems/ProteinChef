package app.te.protein_chef.presentation.my_orders.ui_state

import android.content.Context
import android.view.View
import app.te.protein_chef.R
import app.te.protein_chef.presentation.my_orders.listeners.MyOrdersListener

class MyOrdersEmptyUiState() : MyOrdersUiState {
  override fun getLayoutRes(): Int = R.layout.item_empty_order

  override fun bind(item: View?, position: Int, context: Context,myOrdersListener: MyOrdersListener) {
    item ?: return
  }

  override fun getId(): Int = 0
}