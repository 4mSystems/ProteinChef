package app.grand.tafwak.presentation.meals.ui_state

import android.content.Context
import android.view.View
import app.grand.tafwak.presentation.meals.listeners.MealsListener

interface MealsUiState {
  fun getLayoutRes(): Int
  fun bind(item: View?, position: Int, context: Context, mealsListener: MealsListener)
  fun getId(): Int
}