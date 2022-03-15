package app.grand.tafwak.presentation.my_coupons.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.grand.tafwak.presentation.my_coupons.MyCouponsEventListener
import app.grand.tafwak.presentation.my_coupons.ui_state.MyCouponsUiState
import com.structure.base_mvvm.R

class MyCouponsAdapter(val myCouponsEventListener: MyCouponsEventListener) :
  RecyclerView.Adapter<MyCouponsAdapter.ViewHolder>() {
  lateinit var context: Context
  private val differCallback = object : DiffUtil.ItemCallback<MyCouponsUiState>() {
    override fun areItemsTheSame(oldItem: MyCouponsUiState, newItem: MyCouponsUiState): Boolean {
      return oldItem.getId() == newItem.getId()
    }

    override fun areContentsTheSame(oldItem: MyCouponsUiState, newItem: MyCouponsUiState): Boolean {
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
    return differ.currentList[position]?.getLayoutRes() ?: R.layout.item_empty_coupon
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindItem(differ.currentList[position], position)
  }

  override fun getItemCount(): Int {
    return differ.currentList.size
  }

  inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bindItem(item: MyCouponsUiState?, position: Int) {
      item?.bind(itemView, position, context, myCouponsEventListener)
    }
  }

}