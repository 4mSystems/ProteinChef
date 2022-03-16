package app.te.protein_chef.presentation.meals.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.presentation.base.utils.SingleLiveEvent
import app.te.protein_chef.presentation.meals.listeners.MealsListener
import app.te.protein_chef.presentation.meals.ui_state.MealsUiState
import com.structure.base_mvvm.R

class MealsAdapter(val mealsEventListener: MealsListener) :
  RecyclerView.Adapter<MealsAdapter.ViewHolder>() {
  val liveData = SingleLiveEvent<Int>()
  lateinit var context: Context
  private val differCallback = object : DiffUtil.ItemCallback<MealsUiState>() {
    override fun areItemsTheSame(oldItem: MealsUiState, newItem: MealsUiState): Boolean {
      return oldItem.getId() == newItem.getId()
    }

    override fun areContentsTheSame(oldItem: MealsUiState, newItem: MealsUiState): Boolean {
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
    return differ.currentList[position]?.getLayoutRes() ?: R.layout.item_data_meals
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindItem(differ.currentList[position], position)
  }

  override fun getItemCount(): Int {
    return differ.currentList.size
  }

  inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bindItem(item: MealsUiState?, position: Int) {
      item?.bind(itemView, position, context, mealsEventListener)
    }
  }

}