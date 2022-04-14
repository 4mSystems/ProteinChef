package app.te.protein_chef.presentation.notifications

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentNotificationsBinding
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.setUpAdapter
import app.te.protein_chef.presentation.notifications.adapters.NotificationsAdapter
import app.te.protein_chef.presentation.notifications.listeners.NotificationsListener
import app.te.protein_chef.presentation.notifications.viewModel.NotificationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>(), NotificationsListener {
  private val viewModel: NotificationsViewModel by viewModels()
  private val adapter = NotificationsAdapter(this)

  override
  fun getLayoutId() = R.layout.fragment_notifications

  override fun setBindingVariables() {
    viewModel.getNotifications()
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
      viewModel.notificationsResponse.collect {
        adapter.submitData(it)
        binding.rcNotifications.setUpAdapter(adapter, "1", "1")
      }
    }
  }

  override fun openOrderDetails(orderId: Int) {
  }

}