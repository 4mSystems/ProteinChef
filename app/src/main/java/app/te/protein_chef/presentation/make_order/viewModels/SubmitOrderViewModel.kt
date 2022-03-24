package app.te.protein_chef.presentation.make_order.viewModels

import androidx.lifecycle.SavedStateHandle
import app.te.protein_chef.domain.make_order.entity.MakeOrderRequest
import app.te.protein_chef.presentation.base.BaseViewModel
import app.te.protein_chef.presentation.make_order.ui_state.SubmitOrderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubmitOrderViewModel @Inject constructor(
  stateHandle: SavedStateHandle
) : BaseViewModel() {
  var submitOrderUiState = SubmitOrderUiState()

  init {
    stateHandle.get<MakeOrderRequest>("order_request")?.let { request ->
      submitOrderUiState.makeOrderRequest = request
    }
  }
}