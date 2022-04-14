package app.te.protein_chef.presentation.order_details.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemOrderDaysBinding

class OrderDaysAdapter :
  RecyclerView.Adapter<OrderDaysAdapter.ViewHolder>() {
  var lastPosition = 0
  lateinit var context: Context
  var orderDays: Array<String> = emptyArray()
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.item_order_days, parent, false)
    context = parent.context
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = orderDays[position]
    holder.itemLayoutBinding.datesContainer.setOnClickListener {
      notifyItemChanged(lastPosition)
      lastPosition = position
      notifyItemChanged(lastPosition)
    }
    if (lastPosition == position)
      holder.itemLayoutBinding.datesContainer.setBackgroundResource(R.drawable.corner_view_primary_border)
    else
      holder.itemLayoutBinding.datesContainer.setBackgroundResource(R.drawable.corner_view_gray_border)
    holder.setModel(data)
  }

  override fun getItemCount(): Int {
    return orderDays.size
  }

  fun updateData(data: Array<String>) {
    orderDays = data
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
    lateinit var itemLayoutBinding: ItemOrderDaysBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(item: String) {
      itemLayoutBinding.tvDate.text = item
    }
  }

}