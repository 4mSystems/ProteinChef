package app.grand.tafwak.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.grand.tafwak.presentation.home.ui_state.OffersUiItemState
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemOfferBinding

class OffersAdapter() :
  RecyclerView.Adapter<OffersAdapter.ViewHolder>() {
  private val differCallback = object : DiffUtil.ItemCallback<OffersUiItemState>() {
    override fun areItemsTheSame(
      oldItem: OffersUiItemState,
      newItem: OffersUiItemState
    ): Boolean {
      return oldItem.getOfferId() == newItem.getOfferId()
    }

    override fun areContentsTheSame(
      oldItem: OffersUiItemState,
      newItem: OffersUiItemState
    ): Boolean {
      return oldItem.toString() == newItem.toString()
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false)
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
    lateinit var itemLayoutBinding: ItemOfferBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(item: OffersUiItemState) {
      itemLayoutBinding.uiState = item
    }
  }

}