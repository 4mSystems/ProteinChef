package app.te.protein_chef.presentation.make_order

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentDetectDeliveryBinding
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.presentation.make_order.listener.DetectLocationEventListener
import app.te.protein_chef.presentation.make_order.ui_state.DetectDeliveryItemUiState
import app.te.protein_chef.presentation.make_order.viewModels.DetectDeliveryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetectDeliveryFragment : BaseFragment<FragmentDetectDeliveryBinding>(),
  DetectLocationEventListener {
  private val viewModel: DetectDeliveryViewModel by viewModels()
  private val detectDeliveryItemUiState = DetectDeliveryItemUiState()
  private val args: DetectDeliveryFragmentArgs by navArgs()

  override
  fun getLayoutId() = R.layout.fragment_detect_delivery
  override fun setBindingVariables() {
    binding.eventListener = this
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.getDefaultLocation().collect {
        detectDeliveryItemUiState.defaultLocation = it
        binding.itemUiState = detectDeliveryItemUiState
      }
    }
  }

  override fun addNewLocation() {
    navigateSafe(DetectDeliveryFragmentDirections.actionAccountFragmentToMyLocationsFragment())
  }

  override fun submitOrder() {
    val request = args.orderRequest
    if (detectDeliveryItemUiState.checkDelivery.get()) {
      request.deliveryFees = 70.0
      request.location_id = detectDeliveryItemUiState.defaultLocation.id
    }
    navigateSafe(
      DetectDeliveryFragmentDirections.actionDetectDeliveryFragmentToSubmitOrderFragment(
        request
      )
    )
  }
}