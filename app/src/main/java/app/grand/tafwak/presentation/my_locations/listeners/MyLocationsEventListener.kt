package app.grand.tafwak.presentation.my_locations.listeners

import app.grand.tafwak.presentation.my_locations.ui_state.MyLocationsDataUiState

interface MyLocationsEventListener {
  fun setDefault(myLocationsDataUiState: MyLocationsDataUiState,itemPosition: Int)
  fun removeLocation(locationId: Int,itemPosition: Int)
  fun toAddLocation()
}