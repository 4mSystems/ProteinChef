package app.grand.tafwak.domain.auth.entity.request

import androidx.annotation.Keep
import app.grand.tafwak.presentation.base.utils.Constants

@Keep
data class ForgetPasswordRequest(
  var phone: String = "",
  var type: String = Constants.Verify,
)