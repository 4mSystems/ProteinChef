package app.te.protein_chef.presentation.order_details.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import app.te.protein_chef.R
import app.te.protein_chef.databinding.CancelOrderWarningDialogBinding
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CancelWarningDialog : BottomSheetDialogFragment() {
  lateinit var binding: CancelOrderWarningDialogBinding
  private lateinit var args: CancelWarningDialogArgs
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding =
      DataBindingUtil.inflate(inflater, R.layout.cancel_order_warning_dialog, container, false)
    args = CancelWarningDialogArgs.fromBundle(requireArguments())
    setupObservable()
    return binding.root
  }

  private fun setupObservable() {
    binding.cancel.setOnClickListener {
      dismiss()
      navigateSafe(
        CancelWarningDialogDirections.actionCancelWarningDialogToCancelOrderBankDialog(
          args.orderId
        )
      )
    }
    binding.back.setOnClickListener { dismiss() }
  }
}