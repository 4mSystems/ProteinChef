package app.grand.tafwak.presentation.privacy

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.about.SettingsDataUiState
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import com.structure.base_mvvm.databinding.FragmentPrivacyBinding
import app.grand.tafwak.presentation.privacy.viewModels.PrivacyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PrivacyFragment : BaseFragment<FragmentPrivacyBinding>() {

  private val viewModel: PrivacyViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_privacy



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

  }
}