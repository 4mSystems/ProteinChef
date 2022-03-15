package app.grand.tafwak.presentation.more

import androidx.navigation.NavDirections

interface MoreEventListener {
  fun itemAction(directions: NavDirections,type:Int)
}