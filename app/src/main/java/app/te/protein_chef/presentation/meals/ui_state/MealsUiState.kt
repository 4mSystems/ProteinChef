package app.te.protein_chef.presentation.meals.ui_state

import android.content.Context
import android.view.View
import androidx.annotation.Keep
import app.te.protein_chef.presentation.meals.listeners.MealsListener
@Keep
interface MealsUiState {
  fun getLayoutRes(): Int
  fun bind(item: View?, position: Int, context: Context, mealsListener: MealsListener)
  fun getId(): Int
}