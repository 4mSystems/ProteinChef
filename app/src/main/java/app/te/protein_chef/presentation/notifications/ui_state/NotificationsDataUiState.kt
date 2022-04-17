package app.te.protein_chef.presentation.notifications.ui_state

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemNotificationBinding
import app.te.protein_chef.domain.notifications.entity.NotificationData
import app.te.protein_chef.domain.notifications.entity.NotificationsTypes
import app.te.protein_chef.presentation.notifications.listeners.NotificationsListener

class NotificationsDataUiState(private val notificationData: NotificationData) :
  NotificationsUiState {
  lateinit var notificationsListener: NotificationsListener

  override fun getLayoutRes(): Int = R.layout.item_notification
  override fun bind(
    item: View?,
    position: Int,
    context: Context,
    notificationsListener: NotificationsListener
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemNotificationBinding>(item)
    binding?.uiState = this
    this.notificationsListener = notificationsListener
  }


  override fun getId(): Int = notificationData.id
  fun notificationTitle(): String = notificationData.title
  fun notificationBody(): String = notificationData.body
  fun notificationTime(): String = notificationData.created_at
  fun action() {
    when (notificationData.model_type) {
      NotificationsTypes.Coupon.name -> notificationsListener.openMyCoupons()
      NotificationsTypes.Order.name -> notificationsListener.openOrderDetails(getId())
      NotificationsTypes.Meal.name -> notificationsListener.openOrderDetails(getId())
      NotificationsTypes.Offer.name -> notificationsListener.openHome()
    }
  }
}