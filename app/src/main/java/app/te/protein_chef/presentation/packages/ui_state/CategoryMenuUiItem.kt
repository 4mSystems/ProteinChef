package app.te.protein_chef.presentation.packages.ui_state

import android.content.Context
import androidx.annotation.Keep
import app.te.protein_chef.R

@Keep
data class CategoryMenuUiItem(
  var id: Int = 0,
  var title: String = "",
  var image: String = "",
  var selected: Int = 0
) {
  fun background(): Int = when (selected) {
    2 -> R.drawable.corner_view_dark
    1 -> R.drawable.corner_view_primary_border
    else -> R.drawable.corner_white
  }

  fun textColor(context: Context): Int = when (selected) {
    2 -> context.getColor(R.color.white)
    else -> context.getColor(R.color.colorAccentDark)
  }
}