package app.grand.tafwak.presentation.my_locations.ui_state

import android.content.Context
import android.view.View
import app.grand.tafwak.presentation.my_locations.listeners.MyLocationsEventListener
import com.structure.base_mvvm.R

class MyLocationsEmptyUiState() : MyLocationsUiState {
  override fun getLayoutRes(): Int = R.layout.item_empty_location

  override fun bind(item: View?, position: Int, context: Context,myLocationsEventListener: MyLocationsEventListener) {
    item ?: return
  }

  override fun getId(): Int = 0
}