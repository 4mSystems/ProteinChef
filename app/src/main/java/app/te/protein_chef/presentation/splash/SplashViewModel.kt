package app.te.protein_chef.presentation.splash

import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.general.use_case.GeneralUseCases
import app.te.protein_chef.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val generalUseCases: GeneralUseCases) :
  BaseViewModel() {
  lateinit var eventListener: SplashEventListener
  private val _isLoading = MutableStateFlow(true)
  val isLoading = _isLoading.asStateFlow()

  init {
    viewModelScope.launch {
      delay(4000)
      generalUseCases.checkFirstTimeUseCase().collect { isFirst ->
        if (isFirst) {
          eventListener.openOnBoarding()
        } else {
          generalUseCases.checkLoggedInUserUseCase().collect { user ->
            if (user.name.isNotEmpty() && user.isCompleted == 1)
              eventListener.openHome()
//            else if (user.phone.isNotEmpty() && user.isCompleted == 0)
//              eventListener.openProfile()
            else
              eventListener.openLogin()
          }
        }
        _isLoading.value = false

      }
    }
  }
}