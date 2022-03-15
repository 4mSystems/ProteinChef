package app.grand.tafwak.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.home.models.HomeMainData
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import app.grand.tafwak.presentation.home.adapters.HomeSliderAdapter
import app.grand.tafwak.presentation.home.adapters.OffersAdapter
import app.grand.tafwak.presentation.home.adapters.PackagesAdapter
import app.grand.tafwak.presentation.home.eventListener.HomeEventListener
import app.grand.tafwak.presentation.home.ui_state.HomeUiState
import com.structure.base_mvvm.databinding.FragmentHomeBinding
import app.grand.tafwak.presentation.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeEventListener {

  private val viewModel: HomeViewModel by viewModels()
  private val sliderAdapter = HomeSliderAdapter(this)
  private val packagesAdapter = PackagesAdapter(this)
  private val offersAdapter = OffersAdapter()

  override
  fun getLayoutId() = R.layout.fragment_home

  override
  fun setBindingVariables() {

  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.homeResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            setupUiState(it.value.data)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }

  private fun setupUiState(homeMainData: HomeMainData) {
    val homeUiState = HomeUiState(homeMainData)
    //setupSlider
    sliderAdapter.update(homeUiState.setUpSlider())
    binding.imageSlider.setSliderAdapter(sliderAdapter)
    //setUpPackageAdapter
    packagesAdapter.differ.submitList(homeUiState.setUpPackages())
    binding.rcPackages.setUpAdapter(packagesAdapter, "1", "2")
    // setUpOfferAdapter
    offersAdapter.differ.submitList(homeUiState.setUpOffers())
    binding.rcOffers.setUpAdapter(offersAdapter, "1", "1")
  }


  override fun openSliderUrl() {

  }

  override fun openPackageDetails(packageId: Int, title: String) {
    navigateSafe(
      HomeFragmentDirections.actionHomeFragmentToPackageCategoriesFragment(
        title,
        packageId
      )
    )
  }


}