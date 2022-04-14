package app.te.protein_chef.domain.my_orders.entity.order_details.requests

import androidx.annotation.Keep
import androidx.databinding.ObservableField

@Keep
class FreezeOrderRequest {
  var order_id: Int = 0
  var new_date: String = ""
    set(value) {
      validation.newDateError.set(null)
      field = value
    }
  var old_date: String = ""

  @Transient
  var validation: FreezeOrderValidationException = FreezeOrderValidationException()
}

@Keep
class FreezeOrderValidationException {
  var newDateError: ObservableField<String> = ObservableField<String>()
}
