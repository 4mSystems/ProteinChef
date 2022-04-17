package app.te.protein_chef.presentation.notifications.listeners

interface NotificationsListener {
  fun openOrderDetails(orderId: Int)
  fun openHome()
  fun openMyCoupons()
}