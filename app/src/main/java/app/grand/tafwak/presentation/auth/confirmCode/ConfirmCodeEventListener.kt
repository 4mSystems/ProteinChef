package app.grand.tafwak.presentation.auth.confirmCode


interface ConfirmCodeEventListener {
  fun checkCode()
  fun resendCode()
}