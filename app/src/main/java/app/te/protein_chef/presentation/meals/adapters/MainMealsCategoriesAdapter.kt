package app.te.protein_chef.presentation.meals.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.presentation.base.utils.Constants
import app.te.protein_chef.presentation.meals.listeners.MealsListener
import app.te.protein_chef.presentation.packages.ui_state.CategoryMenuUiItem
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemMainMealCategoryBinding

class MainMealsCategoriesAdapter(val mealsListener: MealsListener) :
  RecyclerView.Adapter<MainMealsCategoriesAdapter.ViewHolder>() {
  var lastSelected = -1
  var lastPosition = 0
  var currentPosition = 1
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
      if (position < lastPosition)
        mealsListener.changeCategoryType(Constants.BACK_WORD)
      else
        mealsListener.changeCategoryType(Constants.FORWARD)
    }
    if (lastPosition == position)
      data.selected = 1
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

  fun changeSelected(type: Int) {
    notifyItemChanged(lastPosition)
    if (type == Constants.FORWARD) {
      differ.currentList[lastPosition].selected = 2
      currentPosition += 1
      lastPosition += 1
    } else {
      differ.currentList[lastPosition].selected = 0
      lastPosition -= 1
      currentPosition -= 1
    }
    lastSelected = differ.currentList[lastPosition].meal_type_id
    notifyItemChanged(lastPosition)
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