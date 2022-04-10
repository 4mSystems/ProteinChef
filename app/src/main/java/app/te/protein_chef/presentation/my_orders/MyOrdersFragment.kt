package app.te.protein_chef.presentation.my_orders

import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentMyOrdersBinding
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.presentation.meals.adapters.ViewPagerStateAdapter
import app.te.protein_chef.presentation.my_orders.listeners.MyOrdersListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MyOrdersFragment : BaseFragment<FragmentMyOrdersBinding>(), MyOrdersListener {
  override
  fun getLayoutId() = R.layout.fragment_my_orders
  override fun setUpViews() {
    binding.pager.isSaveEnabled = false
    bindUi()
    binding.toolbar.tvTitle.text = getString(R.string.my_meals)
  }

  private fun bindUi() {
    binding.pager.let { viewPager2 ->
      val viewPagerStateAdapter = ViewPagerStateAdapter(this)
      viewPagerStateAdapter.addItem(CurrentOrdersFragment(this))
      viewPagerStateAdapter.addItem(PreviousOrdersFragment(this))
      viewPager2.adapter = viewPagerStateAdapter
      TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
        if (position == 0)
          tab.text = getString(R.string.current_orders)
        else
          tab.text = getString(R.string.previous_orders)
      }.attach()
    }
  }

  override fun openOrderDetails(orderId: Int) {
    navigateSafe(MyOrdersFragmentDirections.actionMyMealsFragmentToOrderDetailsFragment(orderId))
  }

}