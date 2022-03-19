package app.te.protein_chef.presentation.more

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemMoreBinding

class MoreAdapter(val moreEventListener: MoreEventListener) :
  RecyclerView.Adapter<MoreAdapter.ViewHolder>() {
  private val differCallback = object : DiffUtil.ItemCallback<MoreItem>() {
    override fun areItemsTheSame(
      oldItem: MoreItem,
      newItem: MoreItem
    ): Boolean {
      return oldItem.directions == newItem.directions
    }

    override fun areContentsTheSame(
      oldItem: MoreItem,
      newItem: MoreItem
    ): Boolean {
      return oldItem == newItem
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_more, parent, false)
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
    lateinit var itemLayoutBinding: ItemMoreBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(item: MoreItem) {
      itemLayoutBinding.eventListener = moreEventListener
      itemLayoutBinding.itemMore = item
    }
  }

}