package app.te.protein_chef.presentation.order_details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemFreezedDatesBinding
import app.te.protein_chef.presentation.order_details.ui_state.freeze.FreezedItemUiState

class FrozenMealsAdapter() :
  RecyclerView.Adapter<FrozenMealsAdapter.ViewHolder>() {
  private val differCallback = object : DiffUtil.ItemCallback<FreezedItemUiState>() {
    override fun areItemsTheSame(
      oldItem: FreezedItemUiState,
      newItem: FreezedItemUiState
    ): Boolean {
      return oldItem.freezeOldDate == newItem.freezeOldDate
    }

    override fun areContentsTheSame(
      oldItem: FreezedItemUiState,
      newItem: FreezedItemUiState
    ): Boolean {
      return oldItem.toString() == newItem.toString()
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.item_freezed_dates, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = differ.currentList[position]
    holder.setModel(data)
  }

  override fun getItemCount(): Int {
    return differ.currentList.size
  }

  override fun onViewAttachedToWindow(holder: ViewHolder) {
    super.onViewAttachedToWindow(holder)
    holder.bind()
  }

  override fun onViewDetachedFromWindow(holder: ViewHolder) {
    super.onViewDetachedFromWindow(holder)
    holder.unBind()
  }

  inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    lateinit var itemLayoutBinding: ItemFreezedDatesBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(item: FreezedItemUiState) {
      itemLayoutBinding.uiState = item
    }
  }

}