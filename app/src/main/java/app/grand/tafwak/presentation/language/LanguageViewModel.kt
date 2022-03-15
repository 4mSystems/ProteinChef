package app.grand.tafwak.presentation.language

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.general.use_case.GeneralUseCases
import app.grand.tafwak.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(val generalUseCases: GeneralUseCases) :
  BaseViewModel() {
  var selectedLang = ObservableField<String>()
  var isLogged = false

  init {
    viewModelScope.launch {
      generalUseCases.checkLoggedInUserUseCase.invoke().collect {
        if (it.phone.isNotEmpty())
          isLogged = true
      }
    }
  }
}