package app.te.protein_chef.presentation.make_order

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentSubmitOrderBinding
import app.te.protein_chef.domain.make_order.entity.coupon.ApplyCouponRequest
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.handleApiError
import app.te.protein_chef.presentation.base.extensions.hideKeyboard
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.presentation.make_order.listener.SubmitOrderEventListener
import app.te.protein_chef.presentation.make_order.viewModels.SubmitOrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SubmitOrderFragment : BaseFragment<FragmentSubmitOrderBinding>(),
  SubmitOrderEventListener {
  private val viewModel: SubmitOrderViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_submit_order
  override fun setBindingVariables() {
    viewModel.submitOrderUiState.submitOrderEventListener = this
    binding.uiState = viewModel.submitOrderUiState
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.couponResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            viewModel.submitOrderUiState.updateCoupon(it.value.data)
            binding.uiState = viewModel.submitOrderUiState
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
    lifecycleScope.launchWhenResumed {
      viewModel.makeOrderResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            showSuccessOrderCreation()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
  }

  private fun showSuccessOrderCreation() {
    navigateSafe(SubmitOrderFragmentDirections.actionSubmitOrderFragmentToOrderSuccessCreationDialog())
  }

  override fun applyCoupon(applyCouponRequest: ApplyCouponRequest) {
    viewModel.applyCoupon(applyCouponRequest)
  }

  override fun makeOrder() {
    viewModel.makeOrder()
  }

  override fun openMyPromo() {
    navigateSafe(SubmitOrderFragmentDirections.actionSubmitOrderFragmentToCouponsFragment())
  }

}