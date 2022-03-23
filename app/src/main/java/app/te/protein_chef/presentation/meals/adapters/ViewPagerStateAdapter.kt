package app.te.protein_chef.presentation.meals.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerStateAdapter(fm: Fragment) :
  FragmentStateAdapter(fm) {
  private val items: MutableList<Fragment> = mutableListOf()

  override fun getItemCount(): Int = items.size

  override fun createFragment(position: Int): Fragment = items[position]
  fun addItem(fragment: Fragment) {
    items.add(fragment)
  }
}
