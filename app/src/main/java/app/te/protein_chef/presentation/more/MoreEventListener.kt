package app.te.protein_chef.presentation.more

import androidx.navigation.NavDirections

interface MoreEventListener {
  fun itemAction(directions: NavDirections,type:Int)
}