package app.te.protein_chef.presentation.order_details.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.presentation.packages.ui_state.CategoryMenuUiItem
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemMainMealCategoryBinding
import app.te.protein_chef.presentation.order_details.listeners.OrderDetailsListeners

class OrderCategoriesAdapter(private val orderDetailsListeners: OrderDetailsListeners) :
  RecyclerView.Adapter<OrderCategoriesAdapter.ViewHolder>() {
  var lastPosition = 0
  lateinit var context: Context
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
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.item_main_meal_category, parent, false)
    context = parent.context
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = differ.currentList[position]
    holder.itemLayoutBinding.card.setOnClickListener {
      notifyItemChanged(lastPosition)
      lastPosition = position
      orderDetailsListeners.changeCategoryType(data.id)
      notifyItemChanged(lastPosition)
    }
    if (lastPosition == position)
      data.selected = 1
    else
      data.selected = 0
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
    lateinit var itemLayoutBinding: ItemMainMealCategoryBinding

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