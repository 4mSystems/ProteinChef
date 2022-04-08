package app.te.protein_chef.presentation.my_orders

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentCurrentOrdersBinding
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.setUpAdapter
import app.te.protein_chef.presentation.my_orders.adapters.MyOrdersAdapter
import app.te.protein_chef.presentation.my_orders.listeners.MyOrdersListener
import app.te.protein_chef.presentation.my_orders.viewModel.MyCurrentOrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class CurrentOrdersFragment : BaseFragment<FragmentCurrentOrdersBinding>(), MyOrdersListener {
  private val viewModel: MyCurrentOrdersViewModel by viewModels()
  private val adapter = MyOrdersAdapter(this)

  override
  fun getLayoutId() = R.layout.fragment_current_orders

  override fun setBindingVariables() {
    viewModel.currentOrders(lifecycleScope)
  }

  override fun setupObservers() {
    adapter.addLoadStateListener { loadState ->

      if (loadState.refresh is LoadState.Loading && !isDetached) {
        showLoading()
      } else {
        hideLoading()
      }

      if (loadState.source.refresh is LoadState.NotLoading &&
        loadState.append.endOfPaginationReached && (adapter.itemCount
          ?: 0) < 1
      ) {
        // getting the error
        val error = when {
          loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
          loadState.append is LoadState.Error -> loadState.append as LoadState.Error
          loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
          else -> null
        }

        error?.let {
          if (it.error.message != null)
            if (it.error.message?.isNotEmpty() == true)
              Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG)
                .show()
        }
      }
    }

    lifecycleScope.launchWhenStarted {
      viewModel.currentOrdersResponse.collect {
        adapter.submitData(it)
        binding.currentOrders.setUpAdapter(adapter, "1", "1")
      }
    }
  }
}