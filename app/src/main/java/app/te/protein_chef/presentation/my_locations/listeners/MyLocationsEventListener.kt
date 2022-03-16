package app.te.protein_chef.presentation.my_locations.listeners

import app.te.protein_chef.presentation.my_locations.ui_state.MyLocationsDataUiState

interface MyLocationsEventListener {
  fun setDefault(myLocationsDataUiState: MyLocationsDataUiState,itemPosition: Int)
  fun removeLocation(locationId: Int,itemPosition: Int)
  fun toAddLocation()
}