package app.te.protein_chef.presentation.home

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseActivity
import app.te.protein_chef.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var nav: NavController

  override
  fun getLayoutId() = R.layout.activity_home

  override
  fun setUpBottomNavigation() {
//    val menuItems = arrayOf(
//      CbnMenuItem(
//        R.drawable.nav_home_selector, // the icon
//        R.drawable.avd_anim, // the AVD that will be shown in FAB
//        R.id.home_fragment // optional if you use Jetpack Navigation
//      ),
//      CbnMenuItem(
//        R.drawable.nav_my_meals,
//        R.drawable.avd_anim,
//        R.id.myMealsFragment
//      ),
//      CbnMenuItem(
//        R.drawable.ic_user,
//        R.drawable.avd_anim,
//        R.id.accountFragment
//      ),
//      CbnMenuItem(
//        R.drawable.ic_nav_more,
//        R.drawable.avd_anim2,
//        R.id.moreFragment
//      )
//    )
//    binding.bottomNavigationView.setMenuItems(menuItems, 0)
    setUpBottomNavigationWithGraphs()
  }

  private fun setUpBottomNavigationWithGraphs() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.fragment_host_container) as NavHostFragment
    nav = navHostFragment.findNavController()
    appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.home_fragment,
        R.id.myMealsFragment,
        R.id.accountFragment,
        R.id.moreFragment
      )
    )
    setSupportActionBar(binding.toolbar)
    setupActionBarWithNavController(nav, appBarConfiguration)
    binding.bottomNavigationView.setupWithNavController(nav)
//    binding.toolbar.navigationIcon = null
    navChangeListener()
  }

  @SuppressLint("UseCompatLoadingForDrawables")
  private fun navChangeListener() {
    nav.addOnDestinationChangedListener { _, destination, _ ->
      if (destination.id == R.id.home_fragment
        || destination.id == R.id.myMealsFragment
        || destination.id == R.id.accountFragment
        || destination.id == R.id.moreFragment
      ) {
        binding.bottomNavigationView.visibility = View.VISIBLE
        binding.toolbar.visibility = View.GONE
      } else {
        binding.bottomNavigationView.visibility = View.GONE
        binding.toolbar.visibility = View.VISIBLE
//        binding.toolbar.navigationIcon = getDrawable(R.drawable.ic_back)
//        binding.toolbar.setNavigationIconTint(getColor(R.color.white))
      }
    }
  }


  override fun onSupportNavigateUp(): Boolean {
    return nav.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return item.onNavDestinationSelected(nav) || super.onOptionsItemSelected(item)
  }

}