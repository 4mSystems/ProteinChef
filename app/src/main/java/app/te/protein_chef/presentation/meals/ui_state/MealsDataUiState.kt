package app.te.protein_chef.presentation.meals.ui_state

import android.content.Context
import android.view.View
import androidx.annotation.Keep
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.domain.meals.entity.MainMealType
import app.te.protein_chef.presentation.meals.listeners.MealItemListener
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemDataMealsBinding

@Keep
class MealsDataUiState(val mealType: MainMealType) : MealsUiState {
  var position: Int = 0
  override fun getLayoutRes(): Int = R.layout.item_data_meals
  override fun bind(
    item: View?,
    position: Int,
    context: Context, mealsListener: MealItemListener?
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemDataMealsBinding>(item)
    binding?.itemUiState = this
    binding?.eventListener = mealsListener
    this.position = position
  }

  override fun getId(): Int = mealType.id
  fun getMealImage(): String = mealType.image
  fun getMealTitle(): String = mealType.title
  fun getMealSelected(): Boolean = mealType.selected == 1
}