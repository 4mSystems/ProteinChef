package app.te.protein_chef.presentation.more

import androidx.navigation.NavDirections
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.databinding.FragmentMoreBinding
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