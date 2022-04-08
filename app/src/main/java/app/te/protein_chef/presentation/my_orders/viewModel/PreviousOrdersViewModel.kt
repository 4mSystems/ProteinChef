package app.te.protein_chef.presentation.my_orders.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.te.protein_chef.domain.my_orders.use_case.PreviousOrdersUseCase
import app.te.protein_chef.presentation.base.BaseViewModel
import app.te.protein_chef.presentation.my_orders.ui_state.MyOrdersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviousOrdersViewModel @Inject constructor(
  private val previousOrdersUseCase: PreviousOrdersUseCase,
) : BaseViewModel() {

  private val _previousOrdersResponse =
    MutableStateFlow<PagingData<MyOrdersUiState>>(PagingData.empty())
  val previousOrdersResponse = _previousOrdersResponse


  fun previousOrders(coroutineScope: CoroutineScope) {
    viewModelScope.launch {
      previousOrdersUseCase.invoke(coroutineScope)
        .collect { result ->
          _previousOrdersResponse.value = result
          Log.e("previousOrders", "previousOrders: ")
        }

    }

  }

}