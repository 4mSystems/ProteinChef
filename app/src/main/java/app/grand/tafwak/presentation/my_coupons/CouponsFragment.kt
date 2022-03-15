package app.grand.tafwak.presentation.my_coupons

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.grand.tafwak.domain.utils.Resource
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.handleApiError
import app.grand.tafwak.presentation.base.extensions.hideKeyboard
import app.grand.tafwak.presentation.base.extensions.setUpAdapter
import app.grand.tafwak.presentation.base.utils.copyText
import app.grand.tafwak.presentation.base.utils.showSuccessAlert
import app.grand.tafwak.presentation.my_coupons.adapter.MyCouponsAdapter
import app.grand.tafwak.presentation.my_coupons.ui_state.MyCouponsUiState
import app.grand.tafwak.presentation.my_coupons.viewModels.CouponsViewModel
import com.structure.base_mvvm.databinding.FragmentMyCouponsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class CouponsFragment : BaseFragment<FragmentMyCouponsBinding>(), MyCouponsEventListener {
  private val viewModel: CouponsViewModel by viewModels()
  private val adapter = MyCouponsAdapter(this)

  override
  fun getLayoutId() = R.layout.fragment_my_coupons

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.couponsResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            val list: List<MyCouponsUiState> = it.value as List<MyCouponsUiState>
            adapter.differ.submitList(list)
            binding.rcCoupon.setUpAdapter(adapter, "1", "1")
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }

  override fun copy(code: String) {
    copyText(code, requireContext())
    showSuccessAlert(requireActivity(), code.plus("\n").plus(getString(R.string.copied)))
  }
}