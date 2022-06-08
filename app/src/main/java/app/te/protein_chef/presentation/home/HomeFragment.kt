package app.te.protein_chef.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentHomeBinding
import app.te.protein_chef.domain.home.models.HomeMainData
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.presentation.home.adapters.HomeSliderAdapter
import app.te.protein_chef.presentation.home.adapters.OffersAdapter
import app.te.protein_chef.presentation.home.adapters.PackagesAdapter
import app.te.protein_chef.presentation.home.eventListener.HomeEventListener
import app.te.protein_chef.presentation.home.ui_state.HomeUiState
import app.te.protein_chef.presentation.home.viewModels.HomeViewModel
import app.te.protein_chef.presentation.maps.LocationManager
import app.te.protein_chef.presentation.maps.MapExtractedData
import app.te.protein_chef.presentation.maps.PermissionManager
import app.te.protein_chef.presentation.maps.requestAppPermissions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeEventListener {
  private val viewModel: HomeViewModel by viewModels()
  private val sliderAdapter = HomeSliderAdapter(this)
  private val packagesAdapter = PackagesAdapter(this)
  private val offersAdapter = OffersAdapter()

  @Inject
  lateinit var permissionManager: PermissionManager

  @Inject
  lateinit var locationManager: LocationManager

  override
  fun getLayoutId() = R.layout.fragment_home

  override
  fun setBindingVariables() {
    binding.eventListener = this
    if (permissionManager.hasAllLocationPermissions()) {
      checkIfLocationEnabled()
    } else {
      permissionsResult?.launch(permissionManager.getAllLocationPermissions())
    }
  }


  override fun onResume() {
    super.onResume()
    val mapData = getNavigationResultLiveData<MapExtractedData>()
    if (mapData?.value != null) {
      binding.locality.text = mapData.value?.city
      showLoading() // for user to see Loader
      viewModel.getHomeData(mapData.value?.latitude!!, mapData.value?.longitude!!)
    }
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.homeResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
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
    binding.uiState = homeUiState
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

  override fun openNotifications() {
    navigateSafe(HomeFragmentDirections.actionHomeFragmentToNotificationsFragment())
  }

  override fun openMap() {
    navigateSafe(HomeFragmentDirections.actionHomeFragmentToNavMap())
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

  // for location
  private val permissionsResult = requestAppPermissions { allIsGranted, _ ->
    if (allIsGranted) {
      checkIfLocationEnabled()
    } else {
      viewModel.getHomeData(0.0, 0.0)
    }
  }

  private fun checkIfLocationEnabled() {
    showLoading() // for user to see Loader
//      if (locationManager.isLocationEnabled(requireContext())) {
//        getLocationNow()
//      } else {
        viewModel.getHomeData(0.0, 0.0)
//      }

  }

  private fun getLocationNow() {
    locationManager.requestNewLocationData(false) {
      viewModel.getHomeData(it.latitude, it.longitude)
      binding.locality.text =
        locationManager.getLocality(it.latitude, it.longitude, requireContext())
    }
  }

}