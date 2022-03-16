package app.te.protein_chef.presentation.auth.confirmCode

import android.os.CountDownTimer
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.domain.utils.Resource
import com.structure.base_mvvm.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.handleApiError
import app.te.protein_chef.presentation.base.extensions.hideKeyboard
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import com.structure.base_mvvm.databinding.FragmentConfirmCodeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ConfirmCodeFragment : BaseFragment<FragmentConfirmCodeBinding>(), ConfirmCodeEventListener {

  private val viewModel: ConfirmViewModel by viewModels()
  private lateinit var countDownTimer: CountDownTimer

  override
  fun getLayoutId() = R.layout.fragment_confirm_code

  override
  fun setBindingVariables() {
    binding.request = viewModel.request
    binding.eventListener = this
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.verifyResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            if (viewModel.request.type == "verify")
              openCompleteData()
            else
              openChangePassword()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.verifyAccount() })
          }
          Resource.Default -> {
          }
        }
      }
    }
    lifecycleScope.launchWhenResumed {
      viewModel.forgetResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            startTimer()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.verifyAccount() })
          }
          Resource.Default -> {
          }
        }
      }
    }
  }

  private fun openCompleteData() {
    navigateSafe(ConfirmCodeFragmentDirections.actionFragmentConfirmCodeToNavProfile())
  }

  private fun openChangePassword() {
    navigateSafe(ConfirmCodeFragmentDirections.actionFragmentConfirmCodeToChangePasswordFragment())
  }

  override fun onStart() {
    super.onStart()
    startTimer()
  }

  private fun startTimer() {
    countDownTimer = object : CountDownTimer(30000, 1000) {
      override fun onTick(millisUntilFinished: Long) {
        binding.tvForgetTimer.text = (millisUntilFinished / 1000).toString().plus(": 00")
      }

      override fun onFinish() {
        binding.tvLoginForget.isEnabled = true
      }
    }.start()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    countDownTimer.cancel()
  }

  override fun checkCode() {
    viewModel.verifyAccount()
  }

  override fun resendCode() {
    viewModel.resendCode()
  }
}