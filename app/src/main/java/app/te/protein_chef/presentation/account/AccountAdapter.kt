package app.te.protein_chef.presentation.account

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.presentation.account.ui_state.AccountItem
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemAccountBinding

class AccountAdapter(val accountEventListener: AccountEventListener) :
  RecyclerView.Adapter<AccountAdapter.ViewHolder>() {
  lateinit var context: Context
  private val differCallback = object : DiffUtil.ItemCallback<AccountItem>() {
    override fun areItemsTheSame(
      oldItem: AccountItem,
      newItem: AccountItem
    ): Boolean {
      return oldItem.navDirections == newItem.navDirections
    }

    override fun areContentsTheSame(
      oldItem: AccountItem,
      newItem: AccountItem
    ): Boolean {
      return oldItem == newItem
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_account, parent, false)
    context = parent.context
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
    lateinit var itemLayoutBinding: ItemAccountBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(itemAccount: AccountItem) {
      itemLayoutBinding.eventListener = accountEventListener
      itemLayoutBinding.itemAccount = itemAccount
    }
  }

}