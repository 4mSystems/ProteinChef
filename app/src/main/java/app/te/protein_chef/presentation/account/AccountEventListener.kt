package app.te.protein_chef.presentation.account

import androidx.navigation.NavDirections

interface AccountEventListener {
  fun itemAction(directions: NavDirections?)
}