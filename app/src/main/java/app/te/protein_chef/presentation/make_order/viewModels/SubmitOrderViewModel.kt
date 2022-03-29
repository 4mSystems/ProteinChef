package app.te.protein_chef.presentation.make_order.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.make_order.entity.MakeOrderRequest
import app.te.protein_chef.domain.make_order.entity.coupon.ApplyCouponRequest
import app.te.protein_chef.domain.make_order.entity.coupon.Coupon
import app.te.protein_chef.domain.make_order.use_case.ApplyCouponUseCase
import app.te.protein_chef.domain.make_order.use_case.MakeOrderUseCase
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import app.te.protein_chef.presentation.make_order.ui_state.SubmitOrderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubmitOrderViewModel @Inject constructor(
  private val applyCouponUseCase: ApplyCouponUseCase,
  private val makeOrderUseCase: MakeOrderUseCase,
  stateHandle: SavedStateHandle
) : BaseViewModel() {
  var submitOrderUiState = SubmitOrderUiState()
  private val _couponResponse =
    MutableStateFlow<Resource<BaseResponse<Coupon>>>(Resource.Default)
  val couponResponse = _couponResponse
 private val _makeOrderResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val makeOrderResponse = _makeOrderResponse

  init {
    stateHandle.get<MakeOrderRequest>("order_request")?.let { request ->
      submitOrderUiState.makeOrderRequest = request
    }
  }

  fun applyCoupon(applyCouponRequest: ApplyCouponRequest) {
    viewModelScope.launch {
      applyCouponUseCase.invoke(applyCouponRequest)
        .collect { result ->
          _couponResponse.value = result
        }
    }

  }

  fun makeOrder() {
    viewModelScope.launch {
      makeOrderUseCase.invoke(submitOrderUiState.makeOrderRequest)
        .collect { result ->
          _makeOrderResponse.value = result
        }
    }
  }
}