package app.te.protein_chef.presentation.order_details.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import app.te.protein_chef.BR
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FreezeDialogBinding
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.FreezeOrderRequest
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.extensions.handleApiError
import app.te.protein_chef.presentation.base.extensions.hideKeyboard
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.presentation.base.extensions.setUpAdapter
import app.te.protein_chef.presentation.base.utils.Constants
import app.te.protein_chef.presentation.base.utils.showSuccessAlert
import app.te.protein_chef.presentation.order_details.adapters.OrderDaysAdapter
import app.te.protein_chef.presentation.order_details.listeners.FreezeOrderListener
import app.te.protein_chef.presentation.order_details.ui_state.freeze.FreezeUiState
import app.te.protein_chef.presentation.order_details.viewModels.FreezeOrderViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class FreezeOrderDialog : BottomSheetDialogFragment(), FreezeOrderListener {
  lateinit var binding: FreezeDialogBinding
  private val args: FreezeOrderDialogArgs by navArgs()
  private val orderDaysAdapter = OrderDaysAdapter()
  private val freezeUiState = FreezeUiState(this)
  private val viewModel: FreezeOrderViewModel by viewModels()

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val dialog = super.onCreateDialog(savedInstanceState)
    dialog.setOnShowListener { dialogInterface ->
      val bottomSheetDialog = dialogInterface as BottomSheetDialog?
      setupFullHeight(bottomSheetDialog)
    }
    return dialog
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding =
      DataBindingUtil.inflate(inflater, R.layout.freeze_dialog, container, false)
    binding.uiState = freezeUiState
    bindUi()
    setupObservable()
    return binding.root
  }

  private fun bindUi() {
    orderDaysAdapter.updateData(args.orderDays)
    binding.rcOrderDays.setUpAdapter(orderDaysAdapter, "3", "1")
    setFragmentResultListener(Constants.BUNDLE) { _: String, bundle: Bundle ->
      freezeUiState.freezeOrderRequest.new_date = bundle.get(Constants.SELECTED_DATE).toString()
//      binding.text.setText(bundle.get(Constants.SELECTED_DATE).toString())
      freezeUiState.notifyPropertyChanged(BR.freezeOrderRequest)
    }
  }

  private fun setupObservable() {
    lifecycleScope.launchWhenResumed {
      viewModel.feeezeOrderResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            freezeUiState.showLoading()
          }
          is Resource.Success -> {
            freezeUiState.hideLoading()
            showSuccessAlert(requireActivity(), it.value.message)
            val bundle = Bundle()
            setFragmentResult(Constants.BUNDLE, bundle)
            dismiss()
          }
          is Resource.Failure -> {
            freezeUiState.hideLoading()
            handleApiError(it)
          }
        }
      }
    }
  }

  private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog?) {
    val bottomSheet: FrameLayout? =
      bottomSheetDialog?.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
    val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(
      bottomSheet!!
    )
    val layoutParams: ViewGroup.LayoutParams = bottomSheet.layoutParams
    val windowHeight = getWindowHeight()
    layoutParams.height = windowHeight
    bottomSheet.layoutParams = layoutParams
    behavior.state = BottomSheetBehavior.STATE_EXPANDED
  }

  private fun getWindowHeight(): Int {
    // Calculate window height for fullscreen use
    val displayMetrics = DisplayMetrics()
    requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels
  }

  override fun freezeOrder(freezeOrderRequest: FreezeOrderRequest) {
    freezeOrderRequest.old_date = orderDaysAdapter.orderDays[orderDaysAdapter.lastPosition]
    viewModel.freezeOrder(freezeOrderRequest)
  }

  override fun showDateDialog() {
    navigateSafe(FreezeOrderDialogDirections.actionFreezeDialogToDatePickerFragment(orderDaysAdapter.orderDays.last()))
  }

}