package app.te.protein_chef.presentation.profile.changePassword

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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