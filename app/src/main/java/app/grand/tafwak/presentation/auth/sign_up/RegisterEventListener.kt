package app.grand.tafwak.presentation.auth.sign_up

import app.grand.tafwak.presentation.base.events.BaseEventListener

interface RegisterEventListener : BaseEventListener {
  fun signUp()
}