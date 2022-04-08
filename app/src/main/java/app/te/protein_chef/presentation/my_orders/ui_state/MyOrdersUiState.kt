package app.te.protein_chef.presentation.my_orders.ui_state

import android.content.Context
import android.view.View
import app.te.protein_chef.presentation.my_locations.listeners.MyLocationsEventListener
import app.te.protein_chef.presentation.my_orders.listeners.MyOrdersListener

interface MyOrdersUiState {
  fun getLayoutRes(): Int
  fun bind(item: View?, position: Int, context: Context,myOrdersListener: MyOrdersListener)
  fun getId(): Int
}