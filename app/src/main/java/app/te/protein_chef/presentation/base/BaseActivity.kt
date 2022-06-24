package app.te.protein_chef.presentation.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import app.te.protein_chef.presentation.base.extensions.adjustFontScale
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegate
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegateImpl
import java.util.*

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
  private val localeDelegate: LocaleHelperActivityDelegate = LocaleHelperActivityDelegateImpl()
  private var _binding: VB? = null
  open val binding get() = _binding!!
  lateinit var navController: LiveData<NavController>

  override
  fun createConfigurationContext(overrideConfiguration: Configuration): Context {
    val context = super.createConfigurationContext(overrideConfiguration)
    return LocaleHelper.onAttach(context)
  }

  override
  fun getApplicationContext(): Context =
    localeDelegate.getApplicationContext(super.getApplicationContext())

  override
  fun onResume() {
    super.onResume()
    localeDelegate.onResumed(this)
  }

  override
  fun onPause() {
    super.onPause()
    localeDelegate.onPaused()
  }

  override
  fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    appInfo()
    adjustFontScale()
    initViewBinding()
    setContentView(binding.root)

    if (savedInstanceState == null) {
      setUpBottomNavigation()
      setUpNavigationDrawer()
    }
    setUpViews()

  }

  fun appInfo() {
    val ai: ApplicationInfo =
      packageManager.getApplicationInfo(this.packageName, PackageManager.GET_META_DATA)
    try {
//      val ai: ActivityInfo =
//        packageManager.getActivityInfo(this.componentName, PackageManager.GET_META_DATA)
      val bundle = ai.metaData
      if (bundle != null) {
        val apiKey = bundle.getString("com.google.android.geo.API_KEY")
        Log.e(this.javaClass.simpleName, "apiKey = $apiKey")

      }
    } catch (e: PackageManager.NameNotFoundException) {

    } catch (e: NullPointerException) {

    }
  }

  override
  fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    setUpBottomNavigation()
    setUpNavigationDrawer()
  }

  private fun initViewBinding() {
    _binding = DataBindingUtil.setContentView(this, getLayoutId())
    binding.lifecycleOwner = this
    binding.executePendingBindings()
  }

  @LayoutRes
  abstract fun getLayoutId(): Int

  open fun setUpBottomNavigation() {}
  open fun setUpNavigationDrawer() {}

  open fun setUpViews() {}

  open fun updateLocale(language: String) {
    localeDelegate.setLocale(this, Locale(language))
  }

  override
  fun onSupportNavigateUp(): Boolean {
    return navController.value?.navigateUp()!! || super.onSupportNavigateUp()
  }

  override
  fun getDelegate() = localeDelegate.getAppCompatDelegate(super.getDelegate())
}