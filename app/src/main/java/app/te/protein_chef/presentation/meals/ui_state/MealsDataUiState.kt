package app.te.protein_chef.presentation.meals.ui_state

import android.content.Context
import android.view.View
import androidx.annotation.Keep
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.domain.meals.entity.MainMealType
import app.te.protein_chef.presentation.meals.listeners.MealItemListener
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemDataMealsBinding
import app.te.protein_chef.presentation.meals.listeners.MealsListener

@Keep
class MealsDataUiState(val mealType: MainMealType) : MealsUiState {
  var position: Int = 0
  override fun getLayoutRes(): Int = R.layout.item_data_meals
  override fun bind(
    item: View?,
    position: Int,
    context: Context, mealsListener: MealItemListener?,
    mealsEventListener: MealsListener?
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemDataMealsBinding>(item)
    binding?.itemUiState = this
    binding?.mealsItemListener = mealsListener
    binding?.eventListener = mealsEventListener
    this.position = position
  }

  override fun getId(): Int = mealType.meal_id
  fun getMealImage(): String = mealType.image
  fun getMealTitle(): String = mealType.title
  fun getMealSelected(): Boolean = mealType.selected == 1
}