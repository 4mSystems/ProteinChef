package app.te.protein_chef.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.presentation.home.eventListener.HomeEventListener
import app.te.protein_chef.presentation.home.ui_state.PackagesUiItemState
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemPackageBinding

class PackagesAdapter(val homeEventListener: HomeEventListener) :
  RecyclerView.Adapter<PackagesAdapter.ViewHolder>() {
  private val differCallback = object : DiffUtil.ItemCallback<PackagesUiItemState>() {
    override fun areItemsTheSame(
      oldItem: PackagesUiItemState,
      newItem: PackagesUiItemState
    ): Boolean {
      return oldItem.getPackageId() == newItem.getPackageId()
    }

    override fun areContentsTheSame(
      oldItem: PackagesUiItemState,
      newItem: PackagesUiItemState
    ): Boolean {
      return oldItem.toString() == newItem.toString()
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_package, parent, false)
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
    lateinit var itemLayoutBinding: ItemPackageBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(item: PackagesUiItemState) {
      itemLayoutBinding.eventListener = homeEventListener
      itemLayoutBinding.uiState = item
    }
  }

}