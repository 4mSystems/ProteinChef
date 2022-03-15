package app.grand.tafwak.presentation.packages.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.grand.tafwak.presentation.packages.ui_state.PackageCategoryUiItem
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemPackageCategoryBinding

class PackageCategoriesAdapter :
  RecyclerView.Adapter<PackageCategoriesAdapter.ViewHolder>() {
  var lastSelected = -1
  var lastPosition = 0
  private val differCallback = object : DiffUtil.ItemCallback<PackageCategoryUiItem>() {
    override fun areItemsTheSame(
      oldItem: PackageCategoryUiItem,
      newItem: PackageCategoryUiItem
    ): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
      oldItem: PackageCategoryUiItem,
      newItem: PackageCategoryUiItem
    ): Boolean {
      return oldItem.toString() == newItem.toString()
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.item_package_category, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = differ.currentList[position]
    holder.itemLayoutBinding.card.setOnClickListener {
      notifyItemChanged(lastPosition)
      lastPosition = position
      lastSelected = data.id
      notifyItemChanged(position)
    }
    if (position == 0 && lastSelected == -1)
      lastSelected = data.id
    holder.itemLayoutBinding.btnDefault.isChecked = lastPosition == position
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
    lateinit var itemLayoutBinding: ItemPackageCategoryBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(item: PackageCategoryUiItem) {
      itemLayoutBinding.uiState = item
    }
  }

}