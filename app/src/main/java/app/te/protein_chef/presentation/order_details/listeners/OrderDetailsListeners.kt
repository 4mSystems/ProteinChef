package app.te.protein_chef.presentation.order_details.listeners

interface OrderDetailsListeners {
  fun changeCategoryType(categoryId: Int)
  fun cancelOrder()
  fun freezeOrder()
}