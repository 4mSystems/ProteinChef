package app.te.protein_chef.presentation.make_order.ui_state

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import com.structure.base_mvvm.DefaultLocation

class DetectDeliveryItemUiState {
  var defaultLocation: DefaultLocation = DefaultLocation.getDefaultInstance()
  val checkDelivery = ObservableBoolean()

  fun checkDefaultLocation(): Int =
    if (defaultLocation.title.isNotEmpty()) View.VISIBLE else View.GONE

  fun checkEmptyLocation(): Int =
    if (defaultLocation.title.isEmpty()) View.VISIBLE else View.GONE

  fun onCheckedChanged(checked: Boolean) {
    checkDelivery.set(!checked)
    Log.e("onCheckedChanged", "onCheckedChanged: "+checkDelivery.get())
  }
}
