package app.te.protein_chef.presentation.my_orders

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentPreviousOrdersBinding
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.setUpAdapter
import app.te.protein_chef.presentation.my_orders.adapters.MyOrdersAdapter
import app.te.protein_chef.presentation.my_orders.listeners.MyOrdersListener
import app.te.protein_chef.presentation.my_orders.viewModel.PreviousOrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class PreviousOrdersFragment(val myOrdersListener: MyOrdersListener) : BaseFragment<FragmentPreviousOrdersBinding>() {
  private val viewModel: PreviousOrdersViewModel by viewModels()
  private val adapter = MyOrdersAdapter(myOrdersListener)

  override
  fun getLayoutId() = R.layout.fragment_previous_orders
  override fun setBindingVariables() {
    viewModel.previousOrders(lifecycleScope)
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

//    lifecycleScope.launchWhenResumed {
//      viewModel.previousOrdersResponse.collect {
//        adapter.submitData(it)
//        Log.e("setupObservers", "setupObservers: "+adapter.itemCount)
//        binding.previousOrders.setUpAdapter(adapter, "1", "1")
//      }
//    }
    lifecycleScope.launchWhenStarted {
      viewModel.previousOrdersResponse.collect {
        adapter.submitData(it)
        binding.previousOrders.setUpAdapter(adapter, "1", "1")
      }
    }
  }

}