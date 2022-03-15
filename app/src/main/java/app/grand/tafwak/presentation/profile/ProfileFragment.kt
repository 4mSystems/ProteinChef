package app.grand.tafwak.presentation.profile

import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.handleApiError
import app.grand.tafwak.presentation.base.extensions.hideKeyboard
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import app.grand.tafwak.presentation.base.extensions.openActivityAndClearStack
import app.grand.tafwak.presentation.base.utils.showSuccessAlert
import app.grand.tafwak.presentation.home.HomeActivity
import app.grand.tafwak.presentation.profile.viewModels.ProfileViewModel
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(), ProfileEventListener {

  private val viewModel: ProfileViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_profile

  override
  fun setBindingVariables() {
    setupGender()
    binding.eventListener = this
    binding.userUiState = viewModel.userUiState
    binding.request = viewModel.request
  }

  private fun setupGender() {
    val genderList = resources.getStringArray(R.array.gender)
    val genderArrayAdapter = ArrayAdapter(
      requireActivity(),
      R.layout.item_gender,
      genderList
    )
    binding.inGender.setAdapter(genderArrayAdapter)
    binding.inGender.setText(
      binding.inGender.adapter.getItem(if (viewModel.request.gender == "male") 0 else 1).toString(),
      false
    )
    binding.inGender.setOnItemClickListener { parent, _, pos, _ ->
      viewModel.request.gender = if (parent.getItemAtPosition(pos)
          .toString() == getString(R.string.male_gender)
      ) "male" else "female"
    }
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.profileResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openHome(it.value.message)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(
              it,
              retryAction = { viewModel.updateProfile() })
          }

        }
      }
    }
  }

  private fun openHome(message: String) {
    lifecycleScope.launch {
      delay(1000)
      showSuccessAlert(requireActivity(), message)
    }
    openActivityAndClearStack(HomeActivity::class.java)
  }

  override fun updateProfile() {
    viewModel.updateProfile()
  }

  override fun changePassword() {
    navigateSafe(ProfileFragmentDirections.actionProfileFragmentToUpdatePassword())
  }

}