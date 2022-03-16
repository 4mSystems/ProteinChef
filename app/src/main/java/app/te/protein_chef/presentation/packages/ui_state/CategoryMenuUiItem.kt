package app.te.protein_chef.presentation.packages.ui_state

import androidx.annotation.Keep

@Keep
data class CategoryMenuUiItem(
  var id: Int = 0,
  var title: String = "",
  var image: String = ""
)