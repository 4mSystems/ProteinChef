package app.te.protein_chef.domain.auth.entity.request

import androidx.annotation.Keep
import app.te.protein_chef.presentation.base.utils.Constants

@Keep
data class ForgetPasswordRequest(
  var phone: String = "",
  var type: String = Constants.Verify,
)