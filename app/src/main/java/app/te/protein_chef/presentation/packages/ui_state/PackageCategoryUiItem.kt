package app.te.protein_chef.presentation.packages.ui_state

import android.content.Context
import com.structure.base_mvvm.R

data class PackageCategoryUiItem(
  var id: Int = 0,
  var title: String = "",
  var price: Double = 0.0,
  var isSelected: Boolean = false,
) {
  fun getPrice(context: Context): String {
    return price.toString().plus(" ").plus(context.getString(R.string.coin))
  }
}
