package app.te.protein_chef.presentation.notifications.ui_state

import android.content.Context
import android.view.View
import app.te.protein_chef.R
import app.te.protein_chef.presentation.notifications.listeners.NotificationsListener

class NotificationsEmptyUiState() : NotificationsUiState {
  override fun getLayoutRes(): Int = R.layout.item_empty_notifications

  override fun bind(
    item: View?,
    position: Int,
    context: Context,
    notificationsListener: NotificationsListener
  ) {
    item ?: return
  }

  override fun getId(): Int = 0
}