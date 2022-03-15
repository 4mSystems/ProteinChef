package app.grand.tafwak.presentation.meals.ui_state

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import app.grand.tafwak.presentation.meals.listeners.MealsListener
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemDateMealsBinding

class MealsDateUiState(private val date: String, private val day: String) : MealsUiState {
  override fun getLayoutRes(): Int = R.layout.item_location
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
  fun getDate(): String = date.plus(" ").plus(day)

}