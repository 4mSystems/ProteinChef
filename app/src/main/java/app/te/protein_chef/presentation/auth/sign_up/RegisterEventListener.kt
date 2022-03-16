package app.te.protein_chef.presentation.auth.sign_up

import app.te.protein_chef.presentation.base.events.BaseEventListener

interface RegisterEventListener : BaseEventListener {
  fun signUp()
}