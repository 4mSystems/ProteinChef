package app.te.protein_chef.domain.my_orders.entity.order_details.requests

import androidx.annotation.Keep
import androidx.databinding.ObservableField

@Keep
class CancelOrderRequest {
  var name: String = ""
    set(value) {
      validation.nameError.set(null)
      field = value
    }
  var order_id: Int = 0
  var iban: String = ""
    set(value) {
      validation.ibanError.set(null)
      field = value
    }
  var bank_name: String = ""
    set(value) {
      validation.bankNameError.set(null)
      field = value
    }

  @Transient
  var validation: CancelOrderValidationException = CancelOrderValidationException()

}

@Keep
class CancelOrderValidationException {
  var ibanError: ObservableField<String> = ObservableField<String>()
  var bankNameError: ObservableField<String> = ObservableField<String>()
  var nameError: ObservableField<String> = ObservableField<String>()


}
