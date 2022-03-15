package app.grand.tafwak.domain.profile.entity

import androidx.annotation.Keep
import androidx.databinding.ObservableField
import app.grand.tafwak.domain.utils.isValidEmail
import app.grand.tafwak.presentation.base.utils.Constants

@Keep
class UpdatePassword {
  var old_password: String = ""
    set(value) {
      validation.oldPasswordError.set(null)
      field = value
    }
  var password: String = ""
    set(value) {
      validation.newPasswordError.set(null)
      field = value
    }
  var password_confirmation: String = ""
    set(value) {
      validation.newPasswordConfirmError.set(null)
      field = value
    }

  @Transient
  var validation: UpdateValidationException = UpdateValidationException()
}

@Keep
class UpdateValidationException {
  @Transient
  var oldPasswordError: ObservableField<String> = ObservableField<String>()

  @Transient
  var newPasswordError: ObservableField<String> = ObservableField<String>()

  @Transient
  var newPasswordConfirmError: ObservableField<String> = ObservableField<String>()

}