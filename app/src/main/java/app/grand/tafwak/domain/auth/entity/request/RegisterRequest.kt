package app.grand.tafwak.domain.auth.entity.request

import androidx.annotation.Keep
import androidx.databinding.ObservableField
import app.grand.tafwak.domain.utils.BaseRequest

@Keep
open class RegisterRequest() : BaseRequest() {
  var name: String? = null
  var email: String? = null
  var password: String = ""
    set(value) {
      validation.passwordError.set(null)
      field = value
    }
  var password_confirmation: String = ""
    set(value) {
      validation.confirmError.set(null)
      field = value
    }
  var phone: String = ""
    set(value) {
      validation.phoneError.set(null)
      field = value
    }
  var device_token: String = ""
  var validation: RegisterValidationException = RegisterValidationException()

}

class RegisterValidationException {
  var phoneError: ObservableField<String> = ObservableField<String>()
  var passwordError: ObservableField<String> = ObservableField<String>()
  var confirmError: ObservableField<String> = ObservableField<String>()


}
