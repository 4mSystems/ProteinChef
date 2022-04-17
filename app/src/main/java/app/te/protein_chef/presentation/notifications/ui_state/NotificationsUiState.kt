package app.te.protein_chef.presentation.notifications.ui_state

import android.content.Context
import android.view.View
import app.te.protein_chef.presentation.notifications.listeners.NotificationsListener

interface NotificationsUiState {
  fun getLayoutRes(): Int
  fun bind(
    item: View?,
    position: Int,
    context: Context,
    notificationsListener: NotificationsListener
  )

  fun getId(): Int
}