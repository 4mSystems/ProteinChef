package app.grand.tafwak.presentation.auth.sign_up

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.presentation.base.utils.Constants
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import app.grand.tafwak.presentation.base.utils.getDeviceId
import com.structure.base_mvvm.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(), RegisterEventListener {

  private val viewModel: SignUpViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_sign_up

  override
  fun setBindingVariables() {
    binding.request = viewModel.registerRequest
    binding.eventListener = this
    viewModel.registerRequest.device_token = getDeviceId(requireActivity())
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.registerResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openConfirmCode()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.register() })
          }

        }
      }
    }
  }

  private fun openConfirmCode() {
    navigateSafe(
      SignUpFragmentDirections.actionOpenConfirmCodeFragment(
        viewModel.registerRequest.phone,
        Constants.Verify
      )
    )
  }

  override fun signUp() {
    viewModel.register()
  }

  override fun back() {
    backToPreviousScreen()
  }
}