package app.grand.tafwak.presentation.about

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.about.viewModels.AboutViewModel
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentAboutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AboutFragment : BaseFragment<FragmentAboutBinding>() {

  private val viewModel: AboutViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_about


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