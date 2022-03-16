package app.te.protein_chef.presentation.contactUs.viewModels

import androidx.lifecycle.SavedStateHandle
import app.te.protein_chef.domain.settings.models.ContactUsRequest
import app.te.protein_chef.domain.settings.use_case.SettingsUseCase
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import app.te.protein_chef.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ContactUsViewModel @Inject constructor(
  private val settingsUseCase: SettingsUseCase,
  private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
  var request = ContactUsRequest()
  private val _contactResponse = MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val contactResponse = _contactResponse

  var validationException = SingleLiveEvent<Int>()

  init {
    savedStateHandle.get<String>("type")?.let { type ->
      request.type = type
    }
  }

  fun onContactClicked() {
//    settingsUseCase(request)
//      .catch { exception -> validationException.value = exception.message?.toInt() }
//      .onEach { result ->
//        _contactResponse.value = result
//      }
//      .launchIn(viewModelScope)
  }
}