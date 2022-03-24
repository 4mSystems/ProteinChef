package app.te.protein_chef.presentation.make_order

import androidx.fragment.app.viewModels
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentSubmitOrderBinding
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.make_order.listener.SubmitOrderEventListener
import app.te.protein_chef.presentation.make_order.viewModels.SubmitOrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubmitOrderFragment : BaseFragment<FragmentSubmitOrderBinding>(),
  SubmitOrderEventListener {
  private val viewModel: SubmitOrderViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_submit_order
  override fun setBindingVariables() {
    binding.eventListener = this
    binding.uiState = viewModel.submitOrderUiState
  }

  override fun setupObservers() {

  }

}