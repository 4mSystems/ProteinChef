package app.te.protein_chef.presentation.language

import androidx.fragment.app.viewModels
import app.te.protein_chef.presentation.auth.AuthActivity
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.presentation.base.extensions.openActivityAndClearStack
import app.te.protein_chef.databinding.FragmentLanguageBinding
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