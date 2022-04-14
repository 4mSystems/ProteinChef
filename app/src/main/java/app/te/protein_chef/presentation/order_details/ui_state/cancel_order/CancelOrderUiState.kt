package app.te.protein_chef.presentation.order_details.ui_state.cancel_order

import android.content.Context
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import app.te.protein_chef.BR
import app.te.protein_chef.R
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.CancelOrderRequest
import app.te.protein_chef.presentation.base.BaseUiState
import app.te.protein_chef.presentation.order_details.listeners.CancelOrderListener

class CancelOrderUiState(private val cancelOrderListener: CancelOrderListener) : BaseUiState() {
  val cancelOrderRequest = CancelOrderRequest()

  @Bindable
  var isLoading = ObservableBoolean()

  fun cancelOrder(context: Context) {
    if (checkValidation(context))
      cancelOrderListener.cancelOrder(cancelOrderRequest)
  }

  private fun checkValidation(context: Context): Boolean {
    var isValid = true
    if (cancelOrderRequest.iban.isEmpty()) {
      cancelOrderRequest.validation.ibanError.set(context.getString(R.string.empty_warning))
      isValid = false
    }
    if (cancelOrderRequest.bank_name.isEmpty()) {
      cancelOrderRequest.validation.bankNameError.set(context.getString(R.string.empty_warning))
      isValid = false
    }
    if (cancelOrderRequest.name.isEmpty()) {
      cancelOrderRequest.validation.nameError.set(context.getString(R.string.empty_warning))
      isValid = false
    }
    return isValid
  }

  fun showLoading() {
    isLoading.set(true)
  }

  fun hideLoading() {
    isLoading.set(false)
  }

}