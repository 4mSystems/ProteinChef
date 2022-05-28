package app.te.protein_chef.presentation.splash

import androidx.fragment.app.viewModels
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.presentation.base.extensions.openActivityAndClearStack
import app.te.protein_chef.presentation.home.HomeActivity
import app.te.protein_chef.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(), SplashEventListener {

  private val viewModel: SplashViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_splash

  override
  fun setUpViews() {
    binding.viewModel = viewModel
    viewModel.eventListener = this
  }


  override fun openHome() {
    openActivityAndClearStack(HomeActivity::class.java)
  }

  override fun openLogin() {
    view?.post {
      navigateSafe(SplashFragmentDirections.actionSplashFragmentToLogInFragment())
    }
  }

  override fun openProfile() {
    navigateSafe(SplashFragmentDirections.actionSplashFragmentToNavProfile())
  }

  override fun openOnBoarding() {
    setLanguage("ar")
    navigateSafe(SplashFragmentDirections.actionSplashFragmentToTutorialFragment())
  }

}