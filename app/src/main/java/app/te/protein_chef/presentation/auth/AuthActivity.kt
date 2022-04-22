package app.te.protein_chef.presentation.auth

import android.annotation.SuppressLint
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseActivity
import app.te.protein_chef.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {
  private lateinit var nav: NavController

  override
  fun getLayoutId() = R.layout.activity_auth
  override fun setUpViews() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    nav = navHostFragment.findNavController()
    setSupportActionBar(binding.toolbar)
    setupActionBarWithNavController(nav)
    binding.toolbar.navigationIcon = null
    navChangeListener()
  }

  @SuppressLint("UseCompatLoadingForDrawables")
  private fun navChangeListener() {
    nav.addOnDestinationChangedListener { _, destination, _ ->
      if (destination.id == R.id.splashFragment
        || destination.id == R.id.tutorialFragment
      ) {
        binding.toolbar.visibility = View.GONE

      } else {
        binding.toolbar.visibility = View.VISIBLE
        if (destination.id == R.id.log_in_fragment)
          binding.toolbar.navigationIcon = null
        else
          binding.toolbar.navigationIcon = getDrawable(R.drawable.ic_arrow_back)
      }
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    return nav.navigateUp() || super.onSupportNavigateUp()
  }

}