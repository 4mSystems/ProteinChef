package app.te.protein_chef.presentation.my_orders.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.te.protein_chef.domain.my_orders.use_case.CurrentOrdersUseCase
import app.te.protein_chef.presentation.base.BaseViewModel
import app.te.protein_chef.presentation.my_orders.ui_state.MyOrdersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCurrentOrdersViewModel @Inject constructor(
  private val currentOrdersUseCase: CurrentOrdersUseCase,
) : BaseViewModel() {

  private val _currentOrdersResponse =
    MutableStateFlow<PagingData<MyOrdersUiState>>(PagingData.empty())
  val currentOrdersResponse = _currentOrdersResponse

  fun currentOrders(coroutineScope: CoroutineScope) {
    viewModelScope.launch {
      currentOrdersUseCase.invoke(coroutineScope)
        .collect { result ->
          _currentOrdersResponse.value = result
        }

    }

  }

}