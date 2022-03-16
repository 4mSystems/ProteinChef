package app.te.protein_chef.presentation.auth.log_in

interface LoginEventListener {
  fun openRegister()
  fun openForget()
  fun login()
  fun openConfirm()
  fun openHome()
  fun openProfile()
}