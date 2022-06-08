package app.te.protein_chef.presentation.packages

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.handleApiError
import app.te.protein_chef.presentation.base.extensions.hideKeyboard
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.presentation.base.extensions.setUpAdapter
import app.te.protein_chef.presentation.packages.ui_state.PackageCategoriesEventListener
import app.te.protein_chef.presentation.packages.ui_state.PackageCategoryUiItem
import app.te.protein_chef.presentation.packages.viewModel.PackagesViewModel
import app.te.protein_chef.databinding.FragmentPackageSubCategoriesBinding
import app.te.protein_chef.presentation.packages.adapters.PackageSubCategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class PackageSubCategoriesFragment : BaseFragment<FragmentPackageSubCategoriesBinding>(),
  PackageCategoriesEventListener {
  private val viewModel: PackagesViewModel by viewModels()
  private val adapter = PackageSubCategoriesAdapter()
  private val args: PackageSubCategoriesFragmentArgs by navArgs()

  override
  fun getLayoutId() = R.layout.fragment_package_sub_categories
  override fun setBindingVariables() {
    viewModel.getSubCategory(args.packageTypeId, args.packageId)
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
        PackageSubCategoriesFragmentDirections.actionPackageCategoriesFragmentToMenuDialog(
          adapter.lastSelected,
          args.title
        )
      )
  }

}