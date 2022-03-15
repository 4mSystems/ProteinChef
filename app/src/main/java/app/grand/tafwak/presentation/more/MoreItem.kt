package app.grand.tafwak.presentation.more

import androidx.navigation.NavDirections

data class MoreItem(
  var title: String,
  var directions: NavDirections,
  var type: Int,
)
