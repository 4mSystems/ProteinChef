package app.te.protein_chef.presentation.shared.web_view

import app.te.protein_chef.domain.general.repository.GeneralRepository
import app.te.protein_chef.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(private val generalRepository: GeneralRepository) :
  BaseViewModel()