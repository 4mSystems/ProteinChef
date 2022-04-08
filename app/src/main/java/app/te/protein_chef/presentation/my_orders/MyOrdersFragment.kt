package app.te.protein_chef.presentation.my_orders

import androidx.fragment.app.viewModels
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentMyOrdersBinding
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.meals.adapters.ViewPagerStateAdapter
import app.te.protein_chef.presentation.meals.viewModels.MealsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MyOrdersFragment : BaseFragment<FragmentMyOrdersBinding>() {
  private val viewModel: MealsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_my_orders
  override fun setUpViews() {
    bindUi()
    binding.toolbar.tvTitle.text = getString(R.string.my_meals)
  }

  private fun bindUi() {
    binding.pager.let { viewPager2 ->
      val viewPagerStateAdapter = ViewPagerStateAdapter(this)
      viewPagerStateAdapter.addItem(CurrentOrdersFragment())
      viewPagerStateAdapter.addItem(PreviousOrdersFragment())
      viewPager2.adapter = viewPagerStateAdapter
      TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
        if (position == 0)
          tab.text = getString(R.string.current_orders)
        else
          tab.text = getString(R.string.previous_orders)
      }.attach()
    }
  }

}