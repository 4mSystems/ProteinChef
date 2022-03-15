package app.grand.tafwak.presentation.splash

import android.util.Log
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.general.use_case.GeneralUseCases
import app.grand.tafwak.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val generalUseCases: GeneralUseCases) :
  BaseViewModel() {
  lateinit var eventListener: SplashEventListener

  init {
    viewModelScope.launch {
      delay(2000)
      generalUseCases.checkFirstTimeUseCase().collect { isFirst ->
        if (isFirst) {
          eventListener.openOnBoarding()
        } else {
          generalUseCases.checkLoggedInUserUseCase().collect { user ->
            Log.e("SplashViewModel", ":" + user.phone)
            if (user.name.isNotEmpty() && user.isCompleted == 1)
              eventListener.openHome()
            else if (user.phone.isNotEmpty() && user.isCompleted == 0)
              eventListener.openProfile()
            else
              eventListener.openLogin()
          }
        }
      }
    }
  }

}