package app.te.protein_chef.presentation.order_details.ui_state.freeze

import android.content.Context
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import app.te.protein_chef.BR
import app.te.protein_chef.R
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.CancelOrderRequest
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.FreezeOrderRequest
import app.te.protein_chef.presentation.base.BaseUiState
import app.te.protein_chef.presentation.order_details.listeners.CancelOrderListener
import app.te.protein_chef.presentation.order_details.listeners.FreezeOrderListener

class FreezeUiState(private val freezeOrderListener: FreezeOrderListener) : BaseUiState() {
  @Bindable
   val freezeOrderRequest = FreezeOrderRequest()

  @Bindable
  var isLoading = ObservableBoolean()

  fun freezeOrder(context: Context) {
    if (checkValidation(context))
      freezeOrderListener.freezeOrder(freezeOrderRequest)
  }

  private fun checkValidation(context: Context): Boolean {
    var isValid = true
    if (freezeOrderRequest.new_date.isEmpty()) {
      freezeOrderRequest.validation.newDateError.set(context.getString(R.string.empty_warning))
      isValid = false
    }
    return isValid
  }

  fun showDateDialog() {
    freezeOrderListener.showDateDialog()
  }

  fun showLoading() {
    isLoading.set(true)
  }

  fun hideLoading() {
    isLoading.set(false)
  }

}