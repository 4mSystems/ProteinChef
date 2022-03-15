package app.grand.tafwak.presentation.meals.ui_state

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import app.grand.tafwak.domain.meals.entity.MainMealType
import app.grand.tafwak.presentation.meals.listeners.MealsListener
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemDataMealsBinding
import com.structure.base_mvvm.databinding.ItemDateMealsBinding

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

}