package app.grand.tafwak.presentation.auth.forgot_password

import app.grand.tafwak.presentation.base.events.BaseEventListener

interface ForgetPasswordEventListener : BaseEventListener {
  fun openConfirm()
  fun sendCode()
}