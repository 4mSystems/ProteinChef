package app.grand.tafwak.presentation.my_locations.ui_state

import android.content.Context
import android.view.View
import app.grand.tafwak.presentation.my_locations.listeners.MyLocationsEventListener

interface MyLocationsUiState {
  fun getLayoutRes(): Int
  fun bind(item: View?, position: Int, context: Context,myLocationsEventListener: MyLocationsEventListener)
  fun getId(): Int
}