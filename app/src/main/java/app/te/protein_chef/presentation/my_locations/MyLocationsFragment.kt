package app.te.protein_chef.presentation.my_locations

import android.os.Bundle
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.domain.my_locations.entity.MyLocationDto
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.handleApiError
import app.te.protein_chef.presentation.base.extensions.hideKeyboard
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.presentation.base.extensions.setUpAdapter
import app.te.protein_chef.presentation.base.utils.Constants
import app.te.protein_chef.presentation.base.utils.showSuccessAlert
import app.te.protein_chef.presentation.my_locations.adapter.MyLocationsAdapter
import app.te.protein_chef.presentation.my_locations.listeners.MyLocationsEventListener
import app.te.protein_chef.presentation.my_locations.ui_state.MyLocationsDataUiState
import app.te.protein_chef.presentation.my_locations.ui_state.MyLocationsUiState
import app.te.protein_chef.presentation.my_locations.viewModels.MyLocationsViewModel
import app.te.protein_chef.databinding.FragmentMyLocationsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MyLocationsFragment : BaseFragment<FragmentMyLocationsBinding>(), MyLocationsEventListener {
  private val viewModel: MyLocationsViewModel by viewModels()
  private val adapter = MyLocationsAdapter(this)

  override
  fun getLayoutId() = R.layout.fragment_my_locations
  override fun setBindingVariables() {
    binding.eventListener = this
    getNewLocationResult()
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.locationsResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            val list: List<MyLocationsUiState> = it.value as List<MyLocationsUiState>
            adapter.differ.submitList(list)
            binding.rcCoupon.setUpAdapter(adapter, "1", "1")
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
    lifecycleScope.launchWhenResumed {
      viewModel.removeLocationResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            showSuccessAlert(requireActivity(), it.value.message)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
  }

  private fun getNewLocationResult() {
    setFragmentResultListener(Constants.BUNDLE) { _: String, bundle: Bundle ->
      val myLocationDto = bundle.getParcelable<MyLocationDto>(Constants.NEW_LOCATION)
      if (myLocationDto != null) {
        val myLocationsDataUiState = MyLocationsDataUiState(myLocationDto)
        val newList = adapter.differ.currentList.toMutableList()
        newList.add(myLocationsDataUiState)
        adapter.differ.submitList(newList)
        adapter.notifyItemRangeInserted(newList.size, newList.size)
      }
    }
  }

  override fun setDefault(myLocationsDataUiState: MyLocationsDataUiState, itemPosition: Int) {
    viewModel.setDefaultLocation(myLocationsDataUiState)
    viewModel.getMyCoupons()
  }

  override fun removeLocation(locationId: Int, itemPosition: Int) {
    viewModel.removeLocation(locationId)
    val newList = adapter.differ.currentList.toMutableList()
    newList.removeAt(itemPosition)
    adapter.notifyItemRangeRemoved(itemPosition, newList.size)
    adapter.differ.submitList(newList)
  }

  override fun toAddLocation() {
    navigateSafe(MyLocationsFragmentDirections.actionMyLocationsFragmentToAddLocationFragment())
  }
}