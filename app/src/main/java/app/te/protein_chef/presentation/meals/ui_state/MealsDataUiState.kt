package app.te.protein_chef.presentation.meals.ui_state

import android.content.Context
import android.view.View
import androidx.annotation.Keep
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.domain.meals.entity.MainMealType
import app.te.protein_chef.presentation.meals.listeners.MealsListener
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemDataMealsBinding

@Keep
class MealsDataUiState(private val mealType: MainMealType) : MealsUiState {
  override fun getLayoutRes(): Int = R.layout.item_data_meals
  override fun bind(
    item: View?,
    position: Int,
    context: Context,
    mealsListener: MealsListener
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemDataMealsBinding>(item)
    binding?.itemUiState = this
  }

  override fun getId(): Int = 0
  fun getMealImage(): String = mealType.image
  fun getMealTitle(): String = mealType.title
  fun getMealSelected(): Boolean = mealType.selected == 1
}