package app.te.protein_chef.presentation.order_details.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.my_orders.use_case.OrderDaysUseCase
import app.te.protein_chef.domain.my_orders.use_case.OrderDetailsUseCase
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
  private val orderDetailsUseCase: OrderDetailsUseCase,
  private val orderDaysUseCase: OrderDaysUseCase,
  val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

  private val _orderDetailsResponse =
    MutableStateFlow<Resource<Any>>(Resource.Default)
  val orderDetailsResponse = _orderDetailsResponse

  private val _orderMealResponse =
    MutableStateFlow<Resource<Any>>(Resource.Default)
  val orderMealResponse = _orderMealResponse
  private val _orderDaysResponse =
    MutableStateFlow<Resource<BaseResponse<Array<String>>>>(Resource.Default)
  val orderDaysResponse = _orderDaysResponse

  var orderId: Int = 0

  init {
    savedStateHandle.get<Int>("order_id")?.let { order_id ->
      orderId = order_id
    }
  }

  fun orderDetails() {
    viewModelScope.launch {
      orderDetailsUseCase.invoke(orderId)
        .catch { }.collect { result ->
          _orderDetailsResponse.value = result
        }
      orderDaysUseCase.invoke(orderId)
        .collect { result ->
          _orderDaysResponse.value = result
        }
    }

  }

  fun orderMealsBuCategory(categoryId: Int) {
    viewModelScope.launch {
      orderDetailsUseCase.invoke(orderId, categoryId)
        .catch { }.collect { result ->
          _orderMealResponse.value = result
        }
    }
  }

}