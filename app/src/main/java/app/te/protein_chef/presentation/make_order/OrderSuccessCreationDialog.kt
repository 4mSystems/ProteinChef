package app.te.protein_chef.presentation.make_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.packages.adapters.MenuAdapter
import app.te.protein_chef.presentation.packages.ui_state.CategoryMenuUiItem
import app.te.protein_chef.presentation.packages.viewModel.PackagesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import app.te.protein_chef.R
import app.te.protein_chef.databinding.MenuDialogBinding
import app.te.protein_chef.databinding.OrderCreateSuccessDialogBinding
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class OrderSuccessCreationDialog : BottomSheetDialogFragment() {
  lateinit var binding: OrderCreateSuccessDialogBinding
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding =
      DataBindingUtil.inflate(inflater, R.layout.order_create_success_dialog, container, false)
    binding.openHome.setOnClickListener {
      openActivityAndClearStack(HomeActivity::class.java)
    }
    return binding.root
  }

}