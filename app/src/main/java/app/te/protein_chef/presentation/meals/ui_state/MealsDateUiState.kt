package app.te.protein_chef.presentation.meals.ui_state

import android.content.Context
import android.view.View
import androidx.annotation.Keep
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.presentation.base.utils.DateUtils
import app.te.protein_chef.presentation.base.utils.dayName
import app.te.protein_chef.presentation.meals.listeners.MealsListener
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemDateMealsBinding

@Keep
class MealsDateUiState(private val date: String) : MealsUiState {
  override fun getLayoutRes(): Int = R.layout.item_date_meals
  override fun bind(
    item: View?,
    position: Int,
    context: Context,
    mealsListener: MealsListener
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemDateMealsBinding>(item)
    binding?.itemUiState = this
  }

  override fun getId(): Int = 0
  fun getDate(): String = date.plus(" ").plus(dayName(date))

}