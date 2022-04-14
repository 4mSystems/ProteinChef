package app.te.protein_chef.presentation.notifications.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.te.protein_chef.domain.notifications.use_case.NotificationsUseCase
import app.te.protein_chef.presentation.base.BaseViewModel
import app.te.protein_chef.presentation.notifications.ui_state.NotificationsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
  private val notificationsUseCase: NotificationsUseCase,
) : BaseViewModel() {

  private val _notificationsResponse =
    MutableStateFlow<PagingData<NotificationsUiState>>(PagingData.empty())
  val notificationsResponse = _notificationsResponse

  fun getNotifications() {
    viewModelScope.launch {
      notificationsUseCase.invoke()
        .collect { result ->
          _notificationsResponse.value = result
        }
    }

  }

}