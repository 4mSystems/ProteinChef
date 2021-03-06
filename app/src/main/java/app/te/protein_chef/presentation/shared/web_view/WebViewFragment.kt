package app.te.protein_chef.presentation.shared.web_view

import android.annotation.SuppressLint
import android.graphics.Color
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.databinding.FragmentWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebViewBinding>() {

  private val viewModel: WebViewViewModel by viewModels()

  private var htmlContent: String? = null

  override
  fun getLayoutId() = R.layout.fragment_web_view

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun getFragmentArguments() {
  }

  override
  fun setUpViews() {

    setUpWebView()

    displayHtmlContent()
  }

  @SuppressLint("SetJavaScriptEnabled")
  private fun setUpWebView() {
    binding.webView.apply {
      setBackgroundColor(Color.TRANSPARENT)
      webChromeClient = WebChromeClient()
      webViewClient = object : WebViewClient() {
        override
        fun onPageFinished(view: WebView, url: String) {
          view.loadUrl("javascript:document.body.style.setProperty(\"color\", \"#000000\");")
          hideLoading()
        }
      }
      settings.javaScriptEnabled = true
    }
  }

  private fun displayHtmlContent() {
    if (!htmlContent.isNullOrEmpty()) {
      setContent(htmlContent!!)
    }
  }

  private fun setContent(content: String) {
    binding.webView.loadData(
      content, "text/html", "utf-8"
    )
  }

  private fun backToPreviousScreen() {
    findNavController().popBackStack()
  }
}