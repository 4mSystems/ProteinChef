package app.grand.tafwak.presentation.terms

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.about.SettingsDataUiState
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.*
import com.structure.base_mvvm.databinding.FragmentTermsBinding
import app.grand.tafwak.presentation.terms.viewModels.TermsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TermsFragment : BaseFragment<FragmentTermsBinding>() {

  private val viewModel: TermsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_terms


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