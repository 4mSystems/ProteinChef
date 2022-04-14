package app.te.protein_chef.presentation.notifications.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.R
import app.te.protein_chef.presentation.notifications.listeners.NotificationsListener
import app.te.protein_chef.presentation.notifications.ui_state.NotificationsUiState

class NotificationsAdapter(val notificationsListener: NotificationsListener) :
  PagingDataAdapter<NotificationsUiState, NotificationsAdapter.ViewHolder>(differCallback) {
  lateinit var context: Context

  companion object {
    private val differCallback = object : DiffUtil.ItemCallback<NotificationsUiState>() {
      override fun areItemsTheSame(
        oldItem: NotificationsUiState,
        newItem: NotificationsUiState
      ): Boolean {
        return oldItem.getId() == newItem.getId()
      }

      override fun areContentsTheSame(
        oldItem: NotificationsUiState,
        newItem: NotificationsUiState
      ): Boolean {
        return oldItem.toString() == newItem.toString()
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
    context = parent.context
    return ViewHolder(view)
  }

  override fun getItemViewType(position: Int): Int {
    return getItem(position)?.getLayoutRes() ?: R.layout.item_empty_order
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindItem(getItem(position), position)
  }


  inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bindItem(item: NotificationsUiState?, position: Int) {
      item?.bind(itemView, position, context, notificationsListener)
    }
  }

}