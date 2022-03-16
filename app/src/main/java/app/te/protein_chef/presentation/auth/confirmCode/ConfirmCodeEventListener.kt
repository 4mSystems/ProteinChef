package app.te.protein_chef.presentation.auth.confirmCode


interface ConfirmCodeEventListener {
  fun checkCode()
  fun resendCode()
}