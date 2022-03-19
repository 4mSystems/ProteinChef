package app.te.protein_chef.presentation.my_locations.ui_state

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.domain.my_locations.entity.MyLocationDto
import app.te.protein_chef.presentation.my_locations.listeners.MyLocationsEventListener
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemLocationBinding

class MyLocationsDataUiState(private val myLocationsDto: MyLocationDto) : MyLocationsUiState {
  var itemPosition: Int = 0
  var isDefault: Boolean = myLocationsDto.isDefault
  override fun getLayoutRes(): Int = R.layout.item_location
  override fun bind(
    item: View?,
    position: Int,
    context: Context,
    myLocationsEventListener: MyLocationsEventListener
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemLocationBinding>(item)
    itemPosition = position
    binding?.uiStateLocation = this
    binding?.myLocationsEventListener = myLocationsEventListener
  }


  override fun getId(): Int = myLocationsDto.id
  fun getTitle(): String = myLocationsDto.title
  fun getAddress(): String = myLocationsDto.body

}