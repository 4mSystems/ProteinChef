package app.te.protein_chef.presentation.notifications.ui_state

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemMyOrderBinding
import app.te.protein_chef.databinding.ItemNotificationBinding
import app.te.protein_chef.domain.my_orders.entity.OrderData
import app.te.protein_chef.domain.notifications.entity.NotificationData
import app.te.protein_chef.presentation.my_orders.listeners.MyOrdersListener
import app.te.protein_chef.presentation.notifications.listeners.NotificationsListener
import kotlin.math.roundToInt

class NotificationsDataUiState(private val notificationData: NotificationData) :
  NotificationsUiState {
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
    binding?.eventListener = notificationsListener
  }


  override fun getId(): Int = notificationData.id
  fun notificationTitle(): String = notificationData.title
  fun notificationBody(): String = notificationData.body
//  fun notificationTime(): String = notificationData.
}