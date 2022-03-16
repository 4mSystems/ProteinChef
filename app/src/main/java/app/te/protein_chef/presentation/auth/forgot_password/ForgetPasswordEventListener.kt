package app.te.protein_chef.presentation.auth.forgot_password

import app.te.protein_chef.presentation.base.events.BaseEventListener

interface ForgetPasswordEventListener : BaseEventListener {
  fun openConfirm()
  fun sendCode()
}