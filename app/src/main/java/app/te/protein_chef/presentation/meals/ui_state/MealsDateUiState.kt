package app.te.protein_chef.presentation.meals.ui_state

import android.content.Context
import android.view.View
import androidx.annotation.Keep
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.presentation.base.utils.dayName
import app.te.protein_chef.presentation.meals.adapters.MealsAdapter
import app.te.protein_chef.presentation.meals.listeners.MealItemListener
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemDateMealsBinding
import app.te.protein_chef.presentation.meals.listeners.MealsListener

@Keep
class MealsDateUiState(private val date: String, val typeId: Int) : MealsUiState, MealItemListener {
  var listMeals = mutableListOf<MealsDataUiState>()
  lateinit var mealsAdapter: MealsAdapter
  override fun getLayoutRes(): Int = R.layout.item_date_meals
  override fun bind(
    item: View?,
    position: Int,
    context: Context,
    mealsListener: MealItemListener?,
    mealsEventListener: MealsListener?
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemDateMealsBinding>(item)
    binding?.itemUiState = this
    mealsAdapter = MealsAdapter(this, mealsEventListener)
    mealsAdapter.differ.submitList(listMeals as List<MealsUiState>?)
  }


  override fun getId(): Int = 0

  fun getDate(): String = date.plus(" - ").plus(dayName(date))
  fun requestDate(): String = date
  override fun changeMealItemSelected(position: Int) {
    if (position == 0 && (mealsAdapter.differ.currentList[position] as MealsDataUiState).getMealSelected()) {

    } else {
      mealsAdapter.notifyItemChanged(mealsAdapter.lastPosition)
      (mealsAdapter.differ.currentList[position] as MealsDataUiState).mealType.selected = 1
      (mealsAdapter.differ.currentList[mealsAdapter.lastPosition] as MealsDataUiState).mealType.selected =
        0
      mealsAdapter.lastPosition = position
      mealsAdapter.notifyItemChanged(position)
    }
  }


}