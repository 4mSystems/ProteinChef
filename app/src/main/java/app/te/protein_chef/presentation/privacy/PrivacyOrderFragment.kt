package app.te.protein_chef.presentation.privacy

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.about.SettingsDataUiState
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.databinding.FragmentPrivacyOrderBinding
import app.te.protein_chef.presentation.privacy.viewModels.PrivacyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PrivacyOrderFragment : BaseFragment<FragmentPrivacyOrderBinding>() {

  private val viewModel: PrivacyViewModel by viewModels()
  val args: PrivacyOrderFragmentArgs by navArgs()

  override
  fun getLayoutId() = R.layout.fragment_privacy_order


  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.settingsResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            binding.uiState = SettingsDataUiState(it.value.data.body)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
    binding.next.setOnClickListener {
      navigateSafe(
        PrivacyOrderFragmentDirections.actionPrivacyOrderFragmentToDetectDeliveryFragment(
          args.orderRequest
        )
      )
    }
  }
}