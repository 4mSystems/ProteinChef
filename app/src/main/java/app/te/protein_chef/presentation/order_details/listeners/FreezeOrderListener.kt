package app.te.protein_chef.presentation.order_details.listeners

import app.te.protein_chef.domain.my_orders.entity.order_details.requests.FreezeOrderRequest


interface FreezeOrderListener {
  fun freezeOrder(freezeOrderRequest: FreezeOrderRequest)
  fun showDateDialog()
}