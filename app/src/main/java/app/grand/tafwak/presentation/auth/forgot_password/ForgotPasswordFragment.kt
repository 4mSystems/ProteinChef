package app.grand.tafwak.presentation.auth.forgot_password

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.presentation.base.utils.Constants
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import com.structure.base_mvvm.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>(),
  ForgetPasswordEventListener {
  private val viewModel: ForgotPasswordViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_forgot_password

  override
  fun setBindingVariables() {
    binding.request = viewModel.request
    binding.eventListener = this
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.forgetPasswordResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openConfirm()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.sendCode() })
          }
          Resource.Default -> {
          }
        }
      }
    }
  }

  override fun openConfirm() {
    navigateSafe(
      ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToFragmentConfirmCode(
        viewModel.request.phone,
        Constants.FORGET
      )
    )
  }

  override fun sendCode() {
    viewModel.sendCode()
  }

  override fun back() {
    backToPreviousScreen()
  }


}