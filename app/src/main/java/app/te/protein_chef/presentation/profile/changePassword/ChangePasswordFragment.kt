package app.te.protein_chef.presentation.profile.changePassword

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.auth.changePassword.ChangePasswordEventListener
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.backToPreviousScreen
import app.te.protein_chef.presentation.base.extensions.handleApiError
import app.te.protein_chef.presentation.base.extensions.hideKeyboard
import app.te.protein_chef.presentation.base.utils.showSuccessAlert
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentUpdatePasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangePasswordFragment : BaseFragment<FragmentUpdatePasswordBinding>(),
  ChangePasswordEventListener {

  private val viewModel: ChangePasswordViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_update_password

  override
  fun setBindingVariables() {
    binding.request = viewModel.request
    binding.eventListener = this
  }

  override
  fun setupObservers() {
    lifecycleScope.launch {

      viewModel.userLocalUseCase.invoke().collect { userLocal ->
        Log.e("userLocalUseCase", ": "+userLocal.phone)
        if (userLocal.phone.isEmpty()) {
          binding.inputOldPassword.visibility = View.GONE
          viewModel.request.isForget = true
        }
      }

    }

    lifecycleScope.launchWhenResumed {
      viewModel.changePasswordResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            showSuccessAlert(requireActivity(), it.value.message)
            backToPreviousScreen()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.changePassword() })
          }
          Resource.Default -> {
          }
        }
      }
    }
  }

  override fun changePassword() {
    viewModel.changePassword()
  }


}