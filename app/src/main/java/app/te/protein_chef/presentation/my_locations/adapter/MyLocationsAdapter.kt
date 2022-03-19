package app.te.protein_chef.presentation.my_locations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.presentation.my_locations.listeners.MyLocationsEventListener
import app.te.protein_chef.presentation.my_locations.ui_state.MyLocationsUiState
import app.te.protein_chef.R

class MyLocationsAdapter(val myLocationsEventListener: MyLocationsEventListener) :
  RecyclerView.Adapter<MyLocationsAdapter.ViewHolder>() {
  lateinit var context: Context
  private val differCallback = object : DiffUtil.ItemCallback<MyLocationsUiState>() {
    override fun areItemsTheSame(oldItem: MyLocationsUiState, newItem: MyLocationsUiState): Boolean {
      return oldItem.getId() == newItem.getId()
    }

    override fun areContentsTheSame(oldItem: MyLocationsUiState, newItem: MyLocationsUiState): Boolean {
      return oldItem.toString() == newItem.toString()
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
    context = parent.context
    return ViewHolder(view)
  }

  override fun getItemViewType(position: Int): Int {
    return differ.currentList[position]?.getLayoutRes() ?: R.layout.item_empty_location
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindItem(differ.currentList[position], position)
  }

  override fun getItemCount(): Int {
    return differ.currentList.size
  }

  inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bindItem(item: MyLocationsUiState?, position: Int) {
      item?.bind(itemView, position, context, myLocationsEventListener)
    }
  }

}