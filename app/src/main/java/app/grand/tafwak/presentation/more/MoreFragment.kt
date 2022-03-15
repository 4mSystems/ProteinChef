package app.grand.tafwak.presentation.more

import androidx.navigation.NavDirections
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import com.structure.base_mvvm.databinding.FragmentMoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment : BaseFragment<FragmentMoreBinding>(), MoreEventListener {
  private val moreUiState = MoreUiState()

  override
  fun getLayoutId() = R.layout.fragment_more

  override
  fun setBindingVariables() {
    moreUiState.moreAdapter = MoreAdapter(this)
    moreUiState.context = requireContext()
    moreUiState.updateMoreList()
    binding.uiState = moreUiState
  }

  override fun setUpViews() {
    binding.toolbar.tvTitle.text = getString(R.string.menu_more)
  }

  override fun itemAction(directions: NavDirections, type: Int) {
    when (type) {
      0 -> {
      }
      1 -> {
      }
      2 -> {
      }
      else -> navigateSafe(directions)

    }
  }


}