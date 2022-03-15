package app.grand.tafwak.presentation.home

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseActivity
import com.structure.base_mvvm.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var nav: NavController

  override
  fun getLayoutId() = R.layout.activity_home

  override
  fun setUpBottomNavigation() {
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
    binding.toolbar.navigationIcon = null
    navChangeListener()
  }

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
        binding.toolbar.navigationIcon = getDrawable(R.drawable.ic_back)
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