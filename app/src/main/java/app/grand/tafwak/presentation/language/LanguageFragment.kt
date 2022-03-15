package app.grand.tafwak.presentation.language

import androidx.fragment.app.viewModels
import app.grand.tafwak.presentation.auth.AuthActivity
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import app.grand.tafwak.presentation.base.extensions.openActivityAndClearStack
import com.structure.base_mvvm.databinding.FragmentLanguageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageFragment : BaseFragment<FragmentLanguageBinding>(), LangEventListener {
  private val viewModel: LanguageViewModel by viewModels()


  override
  fun getLayoutId() = R.layout.fragment_language

  override
  fun setUpViews() {
    binding.langEventListener = this
    binding.viewModel = viewModel
    viewModel.selectedLang.set(currentLanguage.language)
  }

  override fun chooseLang() {
    val selectedLang = binding.radioGroup.checkedRadioButtonId
    if (selectedLang == R.id.ar)
      setLanguage("ar")
    else
      setLanguage("en")

    if (viewModel.isLogged)
      openActivityAndClearStack(AuthActivity::class.java)
    else
      navigateSafe(LanguageFragmentDirections.actionLanguageFragmentToLogInFragment())
  }
}