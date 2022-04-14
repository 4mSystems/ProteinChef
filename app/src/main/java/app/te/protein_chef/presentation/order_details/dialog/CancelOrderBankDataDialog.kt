package app.te.protein_chef.presentation.order_details.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import app.te.protein_chef.R
import app.te.protein_chef.databinding.CancelOrderBankDataDialogBinding
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.CancelOrderRequest
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.extensions.handleApiError
import app.te.protein_chef.presentation.base.extensions.hideKeyboard
import app.te.protein_chef.presentation.base.utils.Constants
import app.te.protein_chef.presentation.base.utils.showSuccessAlert
import app.te.protein_chef.presentation.order_details.listeners.CancelOrderListener
import app.te.protein_chef.presentation.order_details.ui_state.cancel_order.CancelOrderUiState
import app.te.protein_chef.presentation.order_details.viewModels.CancelOrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CancelOrderBankDataDialog : BottomSheetDialogFragment(), CancelOrderListener {
  lateinit var binding: CancelOrderBankDataDialogBinding
  private val viewModel: CancelOrderViewModel by viewModels()
  private val cancelOrderUiState = CancelOrderUiState(this)
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding =
      DataBindingUtil.inflate(inflater, R.layout.cancel_order_bank_data_dialog, container, false)
    binding.uiState = cancelOrderUiState
    setupObservable()
    return binding.root
  }

  private fun setupObservable() {
    lifecycleScope.launchWhenResumed {
      viewModel.cancelOrderResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            cancelOrderUiState.showLoading()
          }
          is Resource.Success -> {
            cancelOrderUiState.hideLoading()
            showSuccessAlert(requireActivity(), it.value.message)
            val bundle = Bundle()
            setFragmentResult(Constants.BUNDLE, bundle)
            dismiss()
          }
          is Resource.Failure -> {
            cancelOrderUiState.hideLoading()
            handleApiError(it)
          }
        }
      }
    }
  }

  override fun cancelOrder(cancelOrderRequest: CancelOrderRequest) {
    viewModel.cancelOrder(cancelOrderRequest)

  }
}