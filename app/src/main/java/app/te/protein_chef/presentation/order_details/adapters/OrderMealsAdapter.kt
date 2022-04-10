package app.te.protein_chef.presentation.order_details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemOrderMealsBinding
import app.te.protein_chef.presentation.order_details.ui_state.OrderMealsItemUiStat

class OrderMealsAdapter :
  RecyclerView.Adapter<OrderMealsAdapter.ViewHolder>() {
  private val differCallback = object : DiffUtil.ItemCallback<OrderMealsItemUiStat>() {
    override fun areItemsTheSame(
      oldItem: OrderMealsItemUiStat,
      newItem: OrderMealsItemUiStat
    ): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(
      oldItem: OrderMealsItemUiStat,
      newItem: OrderMealsItemUiStat
    ): Boolean {
      return oldItem.toString() == newItem.toString()
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.item_order_meals, parent, false)
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
    lateinit var itemLayoutBinding: ItemOrderMealsBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(item: OrderMealsItemUiStat) {
      itemLayoutBinding.itemUiState = item
    }
  }

}