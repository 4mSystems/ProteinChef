package app.grand.tafwak.presentation.packages

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.handleApiError
import app.grand.tafwak.presentation.base.extensions.hideKeyboard
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import app.grand.tafwak.presentation.base.extensions.setUpAdapter
import app.grand.tafwak.presentation.packages.adapters.PackageCategoriesAdapter
import app.grand.tafwak.presentation.packages.ui_state.PackageCategoriesEventListener
import app.grand.tafwak.presentation.packages.ui_state.PackageCategoryUiItem
import app.grand.tafwak.presentation.packages.viewModel.PackagesViewModel
import com.structure.base_mvvm.databinding.FragmentPackageCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class PackageCategoriesFragment : BaseFragment<FragmentPackageCategoriesBinding>(),
  PackageCategoriesEventListener {
  private val viewModel: PackagesViewModel by viewModels()
  private val adapter = PackageCategoriesAdapter()

  override
  fun getLayoutId() = R.layout.fragment_package_categories
  override fun setBindingVariables() {
    binding.eventListener = this
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.typesResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            val list: List<PackageCategoryUiItem> = it.value as List<PackageCategoryUiItem>
            adapter.differ.submitList(list)
            binding.rcCategories.setUpAdapter(adapter, "1", "1")
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
  }


  override fun openDates() {
    if (adapter.lastSelected != -1)
      navigateSafe(
        PackageCategoriesFragmentDirections.actionPackageCategoriesFragmentToMenuDialog(
          adapter.lastSelected,
          PackageCategoriesFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).title
        )
      )
  }

}