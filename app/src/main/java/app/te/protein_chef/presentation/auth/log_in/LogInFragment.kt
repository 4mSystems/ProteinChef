package app.te.protein_chef.presentation.auth.log_in

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.presentation.base.utils.Constants
import app.te.protein_chef.domain.utils.Resource
import com.structure.base_mvvm.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.presentation.base.utils.getDeviceId
import com.structure.base_mvvm.databinding.FragmentLogInBinding
import app.te.protein_chef.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(), LoginEventListener {

  private val viewModel: LogInViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_log_in

  override
  fun setBindingVariables() {
    binding.request = viewModel.request
    binding.eventListener = this
    viewModel.request.device_token = getDeviceId(requireActivity())
  }

  override
  fun setupObservers() {

    lifecycleScope.launchWhenResumed {
      viewModel.logInResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openHome()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(
              it,
              retryAction = { viewModel.onLogInClicked() },
              notActiveAction = { openConfirm() })
          }

        }
      }
    }
  }


  override fun openConfirm() {
    navigateSafe(
      LogInFragmentDirections.actionLogInFragmentToFragmentConfirmCode(
        viewModel.request.phone,
        Constants.Verify
      )
    )
  }

  override fun openHome() {
    lifecycleScope.launch {
      viewModel.userLocalUseCase.invoke().collect { user ->
        if (user.name.isNotEmpty() && user.isCompleted == 1) {
          requireActivity().openActivityAndClearStack(HomeActivity::class.java)
        } else openProfile()
      }
    }

  }

  override fun openProfile() {
    navigateSafe(LogInFragmentDirections.actionLogInFragmentToNavProfile())
  }

  override fun openRegister() {
    navigateSafe(LogInFragmentDirections.actionOpenSignUpFragment())
  }

  override fun openForget() {
    navigateSafe(LogInFragmentDirections.actionOpenForgotPasswordFragment())
  }

  override fun login() {
    viewModel.onLogInClicked()
  }
}