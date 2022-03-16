package app.te.protein_chef.presentation.packages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.extensions.handleApiError
import app.te.protein_chef.presentation.base.extensions.hideKeyboard
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.presentation.base.extensions.setUpAdapter
import app.te.protein_chef.presentation.packages.adapters.MenuAdapter
import app.te.protein_chef.presentation.packages.ui_state.CategoryMenuUiItem
import app.te.protein_chef.presentation.packages.viewModel.PackagesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.MenuDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MenuDialog : BottomSheetDialogFragment() {
  private val viewModel: PackagesViewModel by viewModels()
  lateinit var binding: MenuDialogBinding
  private val menuAdapter = MenuAdapter()
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding =
      DataBindingUtil.inflate(inflater, R.layout.menu_dialog, container, false)
    viewModel.getCategoryMenu(MenuDialogArgs.fromSavedStateHandle(viewModel.savedStateHandle).categoryId)
    setupObservable()
    return binding.root
  }

  private fun setupObservable() {
    lifecycleScope.launchWhenResumed {
      viewModel.menuResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            binding.pbProgress.visibility = View.VISIBLE
          }
          is Resource.Success -> {
            binding.pbProgress.visibility = View.GONE
            val list: List<CategoryMenuUiItem> = it.value as List<CategoryMenuUiItem>
            menuAdapter.differ.submitList(list)
            binding.rcPackages.setUpAdapter(menuAdapter, "1", "2")
          }
          is Resource.Failure -> {
            binding.pbProgress.visibility = View.GONE
            handleApiError(it)
          }
        }
      }
    }
    binding.openDate.setOnClickListener {
      if (menuAdapter.differ.currentList.size > 0)
        navigateSafe(
          MenuDialogDirections.actionMenuDialogToOrderStartDateFragment(
            MenuDialogArgs.fromSavedStateHandle(viewModel.savedStateHandle).categoryId,
            MenuDialogArgs.fromSavedStateHandle(viewModel.savedStateHandle).title
          )
        )
    }
  }

}