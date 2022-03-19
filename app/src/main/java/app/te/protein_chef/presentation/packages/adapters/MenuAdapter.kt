package app.te.protein_chef.presentation.packages.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.presentation.packages.ui_state.CategoryMenuUiItem
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemMenuBinding

class MenuAdapter :
  RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
  private val differCallback = object : DiffUtil.ItemCallback<CategoryMenuUiItem>() {
    override fun areItemsTheSame(
      oldItem: CategoryMenuUiItem,
      newItem: CategoryMenuUiItem
    ): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
      oldItem: CategoryMenuUiItem,
      newItem: CategoryMenuUiItem
    ): Boolean {
      return oldItem.toString() == newItem.toString()
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
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
    lateinit var itemLayoutBinding: ItemMenuBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(item: CategoryMenuUiItem) {
      itemLayoutBinding.uiState = item
    }
  }

}