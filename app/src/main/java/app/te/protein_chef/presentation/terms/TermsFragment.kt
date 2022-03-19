package app.te.protein_chef.presentation.terms

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.about.SettingsDataUiState
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.databinding.FragmentTermsBinding
import app.te.protein_chef.presentation.terms.viewModels.TermsViewModel
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