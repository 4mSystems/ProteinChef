package app.te.protein_chef.presentation.my_locations.ui_state

import android.content.Context
import android.view.View
import app.te.protein_chef.presentation.my_locations.listeners.MyLocationsEventListener

interface MyLocationsUiState {
  fun getLayoutRes(): Int
  fun bind(item: View?, position: Int, context: Context,myLocationsEventListener: MyLocationsEventListener)
  fun getId(): Int
}