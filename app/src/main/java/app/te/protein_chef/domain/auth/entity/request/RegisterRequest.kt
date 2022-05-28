package app.te.protein_chef.domain.auth.entity.request

import androidx.annotation.Keep
import androidx.databinding.ObservableField
import app.te.protein_chef.domain.utils.BaseRequest

@Keep
open class RegisterRequest() : BaseRequest() {
  var password: String = ""

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
  var email: String = ""
    set(value) {
      validation.emailError.set(null)
      field = value
    }

  var device_token: String = ""
  var validation: RegisterValidationException = RegisterValidationException()

}

@Keep
class RegisterValidationException {
  var emailError: ObservableField<String> = ObservableField<String>()
  var phoneError: ObservableField<String> = ObservableField<String>()
  var passwordError: ObservableField<String> = ObservableField<String>()
  var confirmError: ObservableField<String> = ObservableField<String>()


}
