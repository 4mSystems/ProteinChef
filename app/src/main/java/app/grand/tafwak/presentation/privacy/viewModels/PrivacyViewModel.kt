package app.grand.tafwak.presentation.privacy.viewModels

import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.settings.models.SettingsData
import app.grand.tafwak.domain.settings.use_case.SettingsUseCase
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.presentation.base.utils.Constants
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PrivacyViewModel @Inject constructor(
  private val settingsUseCase: SettingsUseCase
) : BaseViewModel() {
  private val _settingsResponse =
    MutableStateFlow<Resource<BaseResponse<SettingsData>>>(Resource.Default)
  val settingsResponse = _settingsResponse


  init {
    privacy()
  }

  fun privacy() {
    settingsUseCase(Constants.PRIVACY_TYPE)
      .onEach { result ->
        _settingsResponse.value = result
      }
      .launchIn(viewModelScope)
  }
}