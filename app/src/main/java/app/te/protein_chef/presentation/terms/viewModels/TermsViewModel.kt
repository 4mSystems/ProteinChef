package app.te.protein_chef.presentation.terms.viewModels

import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.domain.settings.models.SettingsData
import app.te.protein_chef.domain.settings.use_case.SettingsUseCase
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.presentation.base.utils.Constants
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TermsViewModel @Inject constructor(
  private val settingsUseCase: SettingsUseCase,
  private val userLocalUseCase: UserLocalUseCase
) : BaseViewModel() {
  private val _settingsResponse =
    MutableStateFlow<Resource<BaseResponse<SettingsData>>>(Resource.Default)
  val settingsResponse = _settingsResponse


  init {
  terms()
  }

  fun terms() {
    settingsUseCase(Constants.TERMS_TYPE)
      .onEach { result ->
        _settingsResponse.value = result
      }
      .launchIn(viewModelScope)
  }
}