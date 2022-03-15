package app.grand.tafwak.presentation.my_locations

import android.os.Bundle
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.my_locations.entity.MyLocationDto
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import app.grand.tafwak.presentation.base.utils.Constants
import app.grand.tafwak.presentation.base.utils.showSuccessAlert
import app.grand.tafwak.presentation.my_locations.listeners.AddLocationEventListener
import app.grand.tafwak.presentation.my_locations.viewModels.AddLocationViewModel
import com.structure.base_mvvm.databinding.FragmentAddLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddLocationFragment : BaseFragment<FragmentAddLocationBinding>(), AddLocationEventListener {

  private val viewModel: AddLocationViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_add_location

  override
  fun setBindingVariables() {
    binding.request = viewModel.addLocationRequest
    binding.eventListener = this
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.addLocationResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            showSuccessAlert(requireActivity(), it.value.message)
            backWithResult(it.value.data)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.addLocation() })
          }

        }
      }
    }
  }

  override fun addLocation() {
    viewModel.addLocation()
  }

  private fun backWithResult(myLocationDto: MyLocationDto) {
    val bundle = Bundle()
    bundle.putParcelable(Constants.NEW_LOCATION, myLocationDto)
    setFragmentResult(Constants.BUNDLE, bundle)
    backToPreviousScreen()
  }
}