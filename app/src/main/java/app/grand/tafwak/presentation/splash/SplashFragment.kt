package app.grand.tafwak.presentation.splash

import androidx.fragment.app.viewModels
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import app.grand.tafwak.presentation.base.extensions.openActivityAndClearStack
import app.grand.tafwak.presentation.home.HomeActivity
import com.structure.base_mvvm.databinding.FragmentSplashBinding
import com.zeugmasolutions.localehelper.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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
    LocaleHelper.setLocale(requireActivity(), Locale("ar"))
    navigateSafe(SplashFragmentDirections.actionSplashFragmentToTutorialFragment())
  }

}