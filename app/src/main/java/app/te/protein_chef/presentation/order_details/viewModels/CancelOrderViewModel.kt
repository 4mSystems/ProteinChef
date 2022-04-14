package app.te.protein_chef.presentation.order_details.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.CancelOrderRequest
import app.te.protein_chef.domain.my_orders.use_case.CancelOrderUseCase
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import app.te.protein_chef.presentation.order_details.dialog.CancelOrderBankDataDialogArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CancelOrderViewModel @Inject constructor(
  private val cancelOrderUseCase: CancelOrderUseCase,
  val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

  private val _cancelOrderResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val cancelOrderResponse = _cancelOrderResponse

  fun cancelOrder(cancelOrderRequest: CancelOrderRequest) {
    cancelOrderRequest.order_id =
      CancelOrderBankDataDialogArgs.fromSavedStateHandle(savedStateHandle).orderId
    viewModelScope.launch {
      cancelOrderUseCase.invoke(cancelOrderRequest)
        .collect { result ->
          _cancelOrderResponse.value = result
        }
    }
  }

}